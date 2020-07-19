package quick.start.parser.jdbcparser;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import quick.start.constant.CommonConstant;
import quick.start.constant.MysqlCommandConstant;
import quick.start.parser.CommandParser;
import quick.start.repositorys.command.CommandForEntity;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.repositorys.condition.ConditionAttribute;
import quick.start.repositorys.jdbc.types.JdbcConditionType;
import quick.start.repositorys.jdbc.types.JdbcSortType;
import quick.start.repositorys.support.SortAttribute;
import quick.start.repositorys.types.ConditionType;
import quick.start.util.ArrayUtils;
import quick.start.util.StringBufferUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class JdbcCommandParser extends CommandParser {

     private ThreadLocal<List<Object>> executeParames = new ThreadLocal<>();

     @Override
     public ExecuteCommandMeta parser(CommandForEntity command) {
          if (ObjectUtils.isEmpty(command)) {
               return ExecuteCommandMeta.of(CommonConstant.NULL, null);
          }
          executeParames.set(new ArrayList<>());
          switch (command.commandType()) {
               case SELECT:
                    return ExecuteCommandMeta.of(parserSelectCommand(command), executeParames.get());
               case COUNT:
                    return ExecuteCommandMeta.of(parserCountCommand(command), executeParames.get());
               case INSERT:
               case DELETE:
                    return ExecuteCommandMeta.of(parserDeleteCommand(command), executeParames.get());
               case UPDATE:
               default:
                    return ExecuteCommandMeta.of(MysqlCommandConstant.NULL, null);
          }
     }

     private String parserDeleteCommand(CommandForEntity command) {
          return StringBufferUtils.of()
                  .append(rightSpace(MysqlCommandConstant.DELETE))
                  .append(rightSpace(MysqlCommandConstant.FROM))
                  .append(command.getMeta().getTableName())
                  .append(parserConditions(command.getConditions()))
                  .toString();
     }

     private String parserCountCommand(CommandForEntity command) {
          return StringBufferUtils.of()
                  .append(rightSpace(MysqlCommandConstant.SELECT))
                  .append(rightSpace(MysqlCommandConstant.COUNT))
                  .append(rightSpace(MysqlCommandConstant.FROM))
                  .append(command.getMeta().getTableName())
                  .append(parserConditions(command.getConditions()))
                  .toString();
     }

     private String parserSelectCommand(CommandForEntity command) {
          return StringBufferUtils.of()
                  .append(rightSpace(MysqlCommandConstant.SELECT))
                  .append(rightSpace(parserColumns(command.getColumnes())))
                  .append(rightSpace(MysqlCommandConstant.FROM))
                  .append(command.getMeta().getTableName())
                  .append(parserConditions(command.getConditions()))
                  .append(parserOrderBy(command.getSorts()))
                  .append(parserLimit(command))
                  .toString();
     }

     private String parserOrderBy(List<SortAttribute> sorts) {
          if (CollectionUtils.isEmpty(sorts)) {
               return CommonConstant.NULL;
          }
          StringBuffer buffer = new StringBuffer(" order by ");
          for (SortAttribute sortAttribute : sorts) {
               buffer
                       .append(rightSpace(sortAttribute.getField()))
                       .append(JdbcSortType.getValue(sortAttribute.getType()))
                       .append(",");
          }
          return buffer.substring(0, buffer.lastIndexOf(","));
     }

     private String parserColumns(Set<String> columns) {
          return CollectionUtils.isEmpty(columns) ? MysqlCommandConstant.ALL_FIELDS : ArrayUtils.join(",", columns);
     }

     private String parserConditions(List<ConditionAttribute> conditions) {
          if (CollectionUtils.isEmpty(conditions)) {
               return CommonConstant.NULL;
          }
          return conditions.stream()
                  .map(x -> (MysqlCommandConstant.SPACE.concat(MysqlCommandConstant.AND).concat(parserConditionAttribute(x))))
                  .reduce(" WHERE 1=1", (a, b) -> (a.concat(b)))
                  .replace("1=1 AND ", "");
     }

     private String parserConditionAttribute(ConditionAttribute condition) {
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
               default:
                    break;
          }
          return buffer.toString();
     }

     private String convertInValues(Collection<? extends Serializable> values) {
          if (CollectionUtils.isEmpty(values)) {
               return CommonConstant.NULL;
          }
          return values.stream()
                  .map(x -> String.valueOf(x))
                  .reduce("", (a, b) -> (a + ",'" + b + "'"))
                  .substring(1);
     }

     /**
      * limit条件
      *
      * @param command
      * @return
      */
     private String parserLimit(CommandForEntity command) {
          if (ObjectUtils.isEmpty(command.getPageSize())) {
               return CommonConstant.NULL;
          }
          if (ObjectUtils.isEmpty(command.getPageNumber())) {
               return MysqlCommandConstant.LIMIT.concat(MysqlCommandConstant.SPACE).concat(String.valueOf(command.getPageSize()));
          }
          int start = command.getPageSize() * (command.getPageNumber() - 1);
          int end = start + command.getPageSize();
          return StringBufferUtils.of()
                  .append(MysqlCommandConstant.SPACE)
                  .append(rightSpace(MysqlCommandConstant.LIMIT))
                  .append(start)
                  .append(",")
                  .append(end)
                  .toString();
     }

}
