package quick.start.repositorys.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import quick.start.entity.Entity;
import quick.start.entity.EntityMapper;
import quick.start.parser.CommandParser;
import quick.start.parser.jdbcparser.JdbcCommandParser;
import quick.start.repositorys.DefaultAbstractRepository;
import quick.start.repositorys.command.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JdbcRepository<E extends Entity> extends DefaultAbstractRepository<E> {

     @Autowired
     private JdbcTemplate jdbcTemplate;

     /**
      * 如果要指定JdbcTemplate，可以通过此方法修改
      *
      * @param jdbcTemplate
      */
     public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
          this.jdbcTemplate = jdbcTemplate;
     }

     @Override
     protected List<E> select(Select<E> select) {
          select.checkTableName();
          ExecuteCommandMeta command = commandParser().parser(select);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("select语句解析异常");
          }
          try {
               return EntityMapper.toEntityList(this.jdbcTemplate.queryForList(command.getCommand(), command.getParames().toArray()), select.getMeta().getEClass());
          } catch (Exception ex) {
               logger.info("查询异常", ex);
               return new ArrayList<>();
          }
     }

     @Override
     protected Integer update(Update<E> update) {
          update.checkTableName();
          ExecuteCommandMeta command = commandParser().parser(update);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("update语句解析异常");
          }
          return this.jdbcTemplate.update(command.getCommand(), command.getParames().toArray());
     }

     @Override
     protected Integer insert(Insert<E> insert) {
          insert.checkTableName();
          ExecuteCommandMeta command = commandParser().parser(insert);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("insert语句解析异常");
          }
          return this.jdbcTemplate.update(command.getCommand(), command.getParames().toArray());
     }

     @Override
     protected Integer delete(Delete<E> delete) {
          delete.checkTableName();
          ExecuteCommandMeta command = commandParser().parser(delete);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("delete语句解析异常");
          }
          return this.jdbcTemplate.update(command.getCommand(), command.getParames().toArray());
     }

     @Override
     protected Integer count(Count<E> count) {
          count.checkTableName();
          ExecuteCommandMeta command = commandParser().parser(count);
          if (StringUtils.isEmpty(command.getCommand())) {
               throw new IllegalArgumentException("count语句解析异常");
          }
          return this.jdbcTemplate.queryForObject(command.getCommand(), command.getParames().toArray(), Integer.class);
     }

     @Override
     public boolean insert(E entity) {
          return false;
     }

     @Override
     public boolean insert(List<E> entitys) {
          return false;
     }

     @Override
     public boolean update(E entity) {
          return false;
     }

     @Override
     public boolean update(List<? extends Serializable> ids, Map<String, Object> data) {
          return false;
     }

     @Override
     public boolean update(String id, Map<String, Object> data) {
          return false;
     }

     @Override
     public boolean update(String id, String key, Object value) {
          return false;
     }

     @Override
     public boolean update(List<? extends Serializable> ids, String key, Object value) {
          return false;
     }

     @Override
     public boolean delete(String column, Collection<? extends Serializable> values) {
          return false;
     }

     @Override
     protected CommandParser commandParser() {
          return new JdbcCommandParser();
     }

}
