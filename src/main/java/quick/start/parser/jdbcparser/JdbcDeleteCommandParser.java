package quick.start.parser.jdbcparser;

import quick.start.constant.MysqlCommandConstant;
import quick.start.repositorys.command.AbstractCommandForEntity;
import quick.start.repositorys.command.CommandType;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.util.StringBufferUtils;

/**
 * @author yuanweiquan
 */
public class JdbcDeleteCommandParser extends JdbcCommandParser {

     @Override
     public ExecuteCommandMeta parser(AbstractCommandForEntity command) {
          return ExecuteCommandMeta.of(parserDeleteCommand(command), executeParames.get());
     }

     private String parserDeleteCommand(AbstractCommandForEntity command) {
          return StringBufferUtils.of()
                  .append(rightSpace(MysqlCommandConstant.DELETE))
                  .append(rightSpace(MysqlCommandConstant.FROM))
                  .append(command.getMeta().getTableName())
                  .append(parserConditions(command.getConditions()))
                  .toString();
     }

     @Override
     public Boolean adapter(AbstractCommandForEntity command) {
          return CommandType.DELETE.equals(command.commandType());
     }
}
