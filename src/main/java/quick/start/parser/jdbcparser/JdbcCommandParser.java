package quick.start.parser.jdbcparser;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import quick.start.constant.MysqlCommandConstant;
import quick.start.parser.CommandParser;
import quick.start.repositorys.command.CommandForEntity;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.repositorys.condition.ConditionAttribute;
import quick.start.repositorys.jdbc.types.JdbcConditionType;
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
               return ExecuteCommandMeta.of(MysqlCommandConstant.NULL, null);
          }
          executeParames.set(new ArrayList<>());
          switch (command.commandType()) {
               case SELECT:
                    return ExecuteCommandMeta.of(parserSelectCommand(command), executeParames.get());
               case COUNT:
               case INSERT:
               case DELETE:
               case UPDATE:
               default:
                    return ExecuteCommandMeta.of(MysqlCommandConstant.NULL, null);
          }
     }

     private String parserSelectCommand(CommandForEntity command) {
          StringBuffer buffer = StringBufferUtils.of()
                  .append(MysqlCommandConstant.SELECT)
                  .append(MysqlCommandConstant.SPACE)
                  .append(parserColumns(command.getColumnes()))
                  .append(MysqlCommandConstant.SPACE)
                  .append(MysqlCommandConstant.FROM)
                  .append(MysqlCommandConstant.SPACE)
                  .append(command.getMeta().getTableName())
                  .append(parserConditions(command.getConditions()))
                  .append(parserLimit(command));
          return buffer.toString();
     }

     private String parserColumns(Set<String> columns) {
          return CollectionUtils.isEmpty(columns) ? MysqlCommandConstant.ALL_FIELDS : ArrayUtils.join(",", columns);
     }

     private String parserConditions(List<ConditionAttribute> conditions) {
          if (CollectionUtils.isEmpty(conditions)) {
               return MysqlCommandConstant.NULL;
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
                            .append(condition.getField())
                            .append(MysqlCommandConstant.SPACE)
                            .append(JdbcConditionType.getValue(conditionType))
                            .append(MysqlCommandConstant.SPACE)
                            .append(MysqlCommandConstant.PLACEHOLDER);
                    executeParames.get().add(condition.getValue());
                    break;
               case IN:
                    buffer
                            .append(condition.getField())
                            .append(MysqlCommandConstant.SPACE)
                            .append(JdbcConditionType.getValue(conditionType))
                            .append(MysqlCommandConstant.SPACE)
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
               return MysqlCommandConstant.NULL;
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
               return MysqlCommandConstant.NULL;
          }
          if (ObjectUtils.isEmpty(command.getPageNumber())) {
               return MysqlCommandConstant.LIMIT.concat(MysqlCommandConstant.SPACE).concat(String.valueOf(command.getPageSize()));
          }
          int start = command.getPageSize() * (command.getPageNumber() - 1);
          int end = start + command.getPageSize();
          return MysqlCommandConstant.LIMIT.concat(MysqlCommandConstant.SPACE).concat(String.valueOf(start)).concat(",").concat(String.valueOf(end));
     }

}
