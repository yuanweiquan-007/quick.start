package quick.start.parser.jdbcparser;

import quick.start.constant.MysqlCommandConstant;
import quick.start.repositorys.command.CommandForEntity;
import quick.start.repositorys.command.CommandType;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.util.StringBufferUtils;

public class JdbcCountCommandParser extends JdbcCommandParser {

     @Override
     public ExecuteCommandMeta parser(CommandForEntity command) {
          return ExecuteCommandMeta.of(parserCountCommand(command), executeParames.get());
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

     @Override
     public Boolean adapter(CommandForEntity command) {
          return CommandType.COUNT.equals(command.commandType());
     }
}
