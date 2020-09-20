package quick.start.parser.jdbcparser;

import quick.start.constant.MysqlCommandConstant;
import quick.start.repositorys.RowValue;
import quick.start.repositorys.command.AbstractCommandForEntity;
import quick.start.repositorys.command.CommandType;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.repositorys.support.SetAttribute;
import quick.start.util.StringBufferUtils;

import java.util.List;

/**
 * @author yuanweiquan
 */
public class JdbcUpdateCommandParser extends JdbcCommandParser {

    @Override
    public ExecuteCommandMeta parser(AbstractCommandForEntity command) {
        return ExecuteCommandMeta.of(parserUpdateCommand(command), executeParames.get());
    }

    private String parserUpdateCommand(AbstractCommandForEntity command) {
        StringBuffer buffer = StringBufferUtils.of()
                .append(rightSpace(MysqlCommandConstant.UPDATE))
                .append(rightSpace(command.getMeta().getTableName()))
                .append(rightSpace(MysqlCommandConstant.SET))
                .append(parserUpdateValues(command.getSetAttributes()))
                .append(parserConditions(command.getConditions()));
        return buffer.toString();
    }

    private String parserUpdateValues(List<SetAttribute> setAttributes) {
        StringBuffer buffer = new StringBuffer();
        for (SetAttribute setAttribute : setAttributes) {
            if (setAttribute.getValue() instanceof RowValue) {
                buffer.append(",").append(setAttribute.getKey()).append(MysqlCommandConstant.EQUAL).append(((RowValue) setAttribute.getValue()).getRowValue());
            } else {
                buffer.append(",").append(setAttribute.getKey()).append(MysqlCommandConstant.EQUAL).append("?");
                executeParames.get().add(setAttribute.getValue());
            }
        }
        return buffer.substring(1);
    }

    @Override
    public Boolean adapter(AbstractCommandForEntity command) {
        return CommandType.UPDATE.equals(command.commandType());
    }

}
