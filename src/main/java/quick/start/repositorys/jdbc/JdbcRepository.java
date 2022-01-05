package quick.start.repositorys.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import quick.start.entity.Entity;
import quick.start.entity.EntityMapper;
import quick.start.parser.jdbcparser.JdbcCommandParser;
import quick.start.repositorys.AbstractDefaultRepository;
import quick.start.repositorys.command.*;
import quick.start.util.FieldUtils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanweiquan
 */
public class JdbcRepository<E extends Entity> extends AbstractDefaultRepository<E, JdbcCommandParser> {

     /**
      * jdbc模板
      */
     @Lazy
     @Autowired
     protected JdbcTemplate jdbcTemplate;

     /**
      * 默认构造方法
      */
     public JdbcRepository() {
          this.commandParser = new JdbcCommandParser();
     }

     /**
      * 设置jdbc模板
      * 如果要指定JdbcTemplate，可以通过此方法修改
      *
      * @param jdbcTemplate jdbc模板
      */
     public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
          this.jdbcTemplate = jdbcTemplate;
     }

     @Override
     protected List<E> select(Select<E> select) {
          ExecuteCommandMeta command = commandParser.parser(select);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("select语句解析异常");
          }
          try {
               List<Map<String, Object>> data = this.jdbcTemplate.queryForList(command.getCommand(), command.getParames().toArray());
               appendMappingIfNecessary(data, select.getMeta().getColumnMapping());
               return EntityMapper.toEntityList(data, select.getMeta().getEntityClass());
          } catch (Exception ex) {
               logger.info("查询异常", ex);
               return new ArrayList<>();
          }
     }

     @Override
     protected Integer update(Update<E> update) {
          ExecuteCommandMeta command = commandParser.parser(update);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("update语句解析异常");
          }
          return this.jdbcTemplate.update(command.getCommand(), command.getParames().toArray());
     }

     @Override
     protected Integer insert(Insert<E> insert) {
          ExecuteCommandMeta command = commandParser.parser(insert);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("insert语句解析异常");
          }
          if (CollectionUtils.isEmpty(insert.getValues())) {
               throw new IllegalArgumentException("插入参数值为空");
          }
          if (insert.getValues().size() == 1) {
               return singleInsert(command, insert.getValues().get(0));
          } else {
               return multipleInsert(command, insert.getValues());
          }
     }

     /**
      * 批量插入
      *
      * @param command
      * @param values
      * @return
      */
     private Integer multipleInsert(ExecuteCommandMeta command, List<E> values) {
          final List<Object> fields = command.getParames();
          List<Map<String, Object>> attributes = values.stream().map(this::parserAttribute).collect(Collectors.toList());
          return this.jdbcTemplate.batchUpdate(command.getCommand(), new BatchPreparedStatementSetter() {
               @Override
               public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    Map<String, Object> attrbute = attributes.get(i);
                    for (int j = 0; j < fields.size(); j++) {
                         preparedStatement.setObject(j + 1, attrbute.get(String.valueOf(fields.get(j))));
                    }
               }

               @Override
               public int getBatchSize() {
                    return attributes.size();
               }
          }).length;
     }

     /**
      * 单个插入
      *
      * @param command
      * @param entity
      * @return
      */
     private Integer singleInsert(ExecuteCommandMeta command, E entity) {
          Map<String, Object> attribute = parserAttribute(entity);
          final List<Object> fields = command.getParames();
          return this.jdbcTemplate.update(command.getCommand(), new PreparedStatementSetter() {
               @Override
               public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    for (int i = 0; i < fields.size(); i++) {
                         preparedStatement.setObject(i + 1, attribute.get(String.valueOf(fields.get(i))));
                    }
               }
          });
     }

     private Map<String, Object> parserAttribute(E entity) {
          Map<String, Object> map = new HashMap<>(16);
          if (!ObjectUtils.isEmpty(entity)) {
               Field[] fields = entity.getClass().getDeclaredFields();
               for (Field field : fields) {
                    try {
                         field.setAccessible(true);
                         map.put(FieldUtils.getFieldName(entity.getClass(), field), field.get(entity));
                    } catch (Exception ex) {
                         ex.printStackTrace();
                    }
               }
          }
          return map;
     }

     @Override
     protected Integer delete(Delete<E> delete) {
          ExecuteCommandMeta command = commandParser.parser(delete);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("delete语句解析异常");
          }
          return this.jdbcTemplate.update(command.getCommand(), command.getParames().toArray());
     }

     @Override
     protected Integer count(Count<E> count) {
          ExecuteCommandMeta command = commandParser.parser(count);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("count语句解析异常");
          }
          return this.jdbcTemplate.queryForObject(command.getCommand(), command.getParames().toArray(), Integer.class);
     }

}
