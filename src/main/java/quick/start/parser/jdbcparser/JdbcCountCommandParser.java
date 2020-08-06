package quick.start.parser.jdbcparser;

import quick.start.constant.MysqlCommandConstant;
import quick.start.repositorys.command.AbstractCommandForEntity;
import quick.start.repositorys.command.CommandType;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.util.StringBufferUtils;

/**
 * @author yuanweiquan
 */
public class JdbcCountCommandParser extends JdbcCommandParser {

     @Override
     public ExecuteCommandMeta parser(AbstractCommandForEntity command) {
          return ExecuteCommandMeta.of(parserCountCommand(command), executeParames.get());
     }

     private String parserCountCommand(AbstractCommandForEntity command) {
          return StringBufferUtils.of()
                  .append(rightSpace(MysqlCommandConstant.SELECT))
                  .append(rightSpace(MysqlCommandConstant.COUNT))
                  .append(rightSpace(MysqlCommandConstant.FROM))
                  .append(command.getMeta().getTableName())
                  .append(parserConditions(command.getConditions()))
                  .toString();
     }

     @Override
     public Boolean adapter(AbstractCommandForEntity command) {
          return CommandType.COUNT.equals(command.commandType());
     }
}
