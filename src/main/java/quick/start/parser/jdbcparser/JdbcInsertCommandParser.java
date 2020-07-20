package quick.start.parser.jdbcparser;

import quick.start.constant.MysqlCommandConstant;
import quick.start.repositorys.command.CommandForEntity;
import quick.start.repositorys.command.CommandType;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.util.StringBufferUtils;

import java.util.Set;

public class JdbcInsertCommandParser extends JdbcCommandParser {

     @Override
     public ExecuteCommandMeta parser(CommandForEntity command) {
          return ExecuteCommandMeta.of(parserInsertCommand(command), executeParames.get());
     }

     private String parserInsertCommand(CommandForEntity command) {
          StringBuffer buffer = StringBufferUtils.of()
                  .append(rightSpace(MysqlCommandConstant.INSERT))
                  .append(rightSpace(MysqlCommandConstant.INTO))
                  .append(command.getMeta().getTableName())
                  .append("(");
          StringBuilder fileds = new StringBuilder();
          StringBuilder values = new StringBuilder();
          Set<String> insertFields = command.getMeta().getInsertFields();
          for (String fieldName : insertFields) {
               fileds.append(",").append(fieldName);
               values.append(",").append("?");
               executeParames.get().add(fieldName);
          }
          buffer.append(fileds.substring(1))
                  .append(rightSpace(")"))
                  .append("VALUES(")
                  .append(values.substring(1))
                  .append(")");
          return buffer.toString();
     }

     @Override
     public Boolean adapter(CommandForEntity command) {
          return CommandType.INSERT.equals(command.commandType());
     }
}
