package quick.start.parser.jdbcparser;

import org.springframework.util.CollectionUtils;
import quick.start.constant.CommonConstant;
import quick.start.constant.MysqlCommandConstant;
import quick.start.repositorys.command.AbstractCommandForEntity;
import quick.start.repositorys.command.CommandType;
import quick.start.repositorys.command.ExecuteCommandMeta;
import quick.start.repositorys.jdbc.types.JdbcSortType;
import quick.start.repositorys.support.SortAttribute;
import quick.start.util.ArrayUtils;
import quick.start.util.StringBufferUtils;

import java.util.List;
import java.util.Set;

/**
 * @author yuanweiquan
 */
public class JdbcSelectCommandParser extends JdbcCommandParser {

     @Override
     public ExecuteCommandMeta parser(AbstractCommandForEntity command) {
          return ExecuteCommandMeta.of(parserSelectCommand(command), executeParames.get());
     }

     /**
      * 解析要查询的字段
      *
      * @param columns
      * @return
      */
     private String parserColumns(Set<String> columns) {
          return CollectionUtils.isEmpty(columns) ? MysqlCommandConstant.ALL_FIELDS : ArrayUtils.join(",", columns);
     }

     private String parserSelectCommand(AbstractCommandForEntity command) {
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

     /**
      * 解析排序
      *
      * @param sorts
      * @return
      */
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

     @Override
     public Boolean adapter(AbstractCommandForEntity command) {
          return CommandType.SELECT.equals(command.commandType());
     }
}
