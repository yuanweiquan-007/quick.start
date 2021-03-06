package quick.start.parser.jdbcparser;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import quick.start.constant.CommonConstant;
import quick.start.constant.MysqlCommandConstant;
import quick.start.parser.AbstractCommandParser;
import quick.start.repositorys.command.AbstractCommandForEntity;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.repositorys.condition.ConditionAttribute;
import quick.start.repositorys.jdbc.types.JdbcConditionType;
import quick.start.repositorys.types.ConditionType;
import quick.start.util.StringBufferUtils;
import quick.start.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author yuanweiquan
 */
public class JdbcCommandParser extends AbstractCommandParser {

    protected static ThreadLocal<List<Object>> executeParames = new ThreadLocal<>();

    private static final List<JdbcCommandParser> JDBC_COMMAND_PARSERS = new ArrayList<>();

    static {
        for (JdbcCommandParser jdbcCommandParser : ServiceLoader.load(JdbcCommandParser.class)) {
            JDBC_COMMAND_PARSERS.add(jdbcCommandParser);
        }
    }

    @Override
    public ExecuteCommandMeta parser(AbstractCommandForEntity command) {
        Assert.notNull(command, "待解析命令对象为空");
        Assert.notNull(command.getMeta().getTableName(), "tableName未设置#可以通过注解@Table注解来设置");
        executeParames.set(new ArrayList<>());
        for (JdbcCommandParser parser : JDBC_COMMAND_PARSERS) {
            if (parser.adapter(command)) {
                return parser.parser(command);
            }
        }
        return ExecuteCommandMeta.of(CommonConstant.NULL, null);
    }

    protected String parserConditions(List<ConditionAttribute> conditions) {
        if (CollectionUtils.isEmpty(conditions)) {
            return CommonConstant.NULL;
        }
        return conditions.stream()
                .map(x -> (MysqlCommandConstant.SPACE.concat(MysqlCommandConstant.AND).concat(parserConditionAttribute(x))))
                .reduce(" WHERE 1=1", (a, b) -> (a.concat(b)))
                .replace("1=1 AND ", "");
    }

    /**
     * 解析条件属性
     *
     * @param condition 条件
     * @return 属性
     */
    protected String parserConditionAttribute(ConditionAttribute condition) {
        StringBuffer buffer = new StringBuffer(MysqlCommandConstant.SPACE);
        ConditionType conditionType = condition.getType();
        switch (conditionType) {
            case EQUAL:
            case NOT_EQUAL:
            case GREATER_THEN:
            case GREATER_THEN_OR_EQUAL:
            case LESS_THEN:
            case LESS_THEN_OR_EQUAL:
                buffer
                        .append(rightSpace(condition.getField()))
                        .append(rightSpace(JdbcConditionType.getValue(conditionType)))
                        .append(MysqlCommandConstant.PLACEHOLDER);
                executeParames.get().add(condition.getValue());
                break;
            case IN:
                buffer
                        .append(rightSpace(condition.getField()))
                        .append(rightSpace(JdbcConditionType.getValue(conditionType)))
                        .append("(")
                        .append(convertInValues((Collection<? extends Serializable>) condition.getValue()))
                        .append(")");
                break;
            case LIKE:
                buffer
                        .append(rightSpace(condition.getField()))
                        .append(rightSpace(JdbcConditionType.getValue(conditionType)))
                        .append(parserLike(condition));
                break;
            default:
                break;
        }
        return buffer.toString();
    }

    protected String parserLike(ConditionAttribute condition) {
        Object value = CommonConstant.NULL;
        if (String.valueOf(condition.getValue()).contains(MysqlCommandConstant.PERCENT)) {
            value = condition.getValue();
        } else {
            value = StringUtils.concat(
                    CommonConstant.NULL,
                    MysqlCommandConstant.PERCENT,
                    String.valueOf(condition.getValue()),
                    MysqlCommandConstant.PERCENT
            );
        }
        return StringBufferUtils.of()
                .append("\"")
                .append(value)
                .append("\"")
                .toString();
    }

    /**
     * in添加值转换
     *
     * @param values 内容集合
     * @return in字符内容
     */
    protected String convertInValues(Collection<? extends Serializable> values) {
        if (CollectionUtils.isEmpty(values)) {
            return CommonConstant.NULL;
        }
        return values.stream()
                .map(x -> String.valueOf(x))
                .reduce("", (a, b) -> (a + ",'" + b + "'"))
                .substring(1);
    }

    protected String parserLimit(AbstractCommandForEntity command) {
        if (ObjectUtils.isEmpty(command.getPageSize())) {
            return CommonConstant.NULL;
        }
        if (ObjectUtils.isEmpty(command.getPageNumber())) {
            return MysqlCommandConstant.LIMIT.concat(MysqlCommandConstant.SPACE).concat(String.valueOf(command.getPageSize()));
        }
        int start = command.getPageSize() * (command.getPageNumber() - 1);
        return StringBufferUtils.of()
                .append(MysqlCommandConstant.SPACE)
                .append(rightSpace(MysqlCommandConstant.LIMIT))
                .append(start)
                .append(",")
                .append(command.getPageSize())
                .toString();
    }

    public Boolean adapter(AbstractCommandForEntity command) {
        return false;
    }
}
