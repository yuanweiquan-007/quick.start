package quick.start.repositorys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;
import quick.start.exception.UniqueException;
import quick.start.parser.CommandParser;
import quick.start.repositorys.command.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public abstract class DefaultAbstractRepository<E extends Entity> implements Repository<E> {

     protected CommandFactory<E> commandFactory;
     protected Logger logger = LoggerFactory.getLogger(getClass());
     
     public DefaultAbstractRepository() {
          commandFactory = new CommandFactory<>(EntityMeta.of(entityClass()));
     }

     protected abstract CommandParser commandParser();

     protected abstract List<E> select(Select<E> select);

     protected abstract Integer update(Update<E> update);

     protected abstract Integer insert(Insert<E> insert);

     protected abstract Integer delete(Delete<E> delete);

     protected abstract Integer count(Count<E> count);

     @Override
     public boolean has(Serializable id) {
          Count<E> count = commandFactory.count();
          count.checkPrimaryKey().equal(count.primaryKey(), id);
          return count(count) > 0;
     }

     @Override
     public E findById(Serializable id) {
          Assert.notNull(id, "查询主键值为空");
          Select<E> select = commandFactory.select();
          select.checkPrimaryKeyAndTableName().equal(select.primaryKey(), id);
          List<E> entitys = select(select);
          if (entitys.size() > 1) {
               throw new UniqueException("根据主键查询出了多条数据");
          }
          return CollectionUtils.isEmpty(entitys) ? select.getMeta().newInstance() : entitys.get(0);
     }

     @Override
     public List<E> findByIds(Collection<? extends Serializable> ids) {
          Assert.notEmpty(ids, "查询主键值为空");
          Select<E> select = commandFactory.select();
          select.checkPrimaryKey().whereIn(select.primaryKey(), ids);
          return select(select);
     }

     @Override
     public List<E> find() {
          return select(commandFactory.select());
     }

     @Override
     public List<E> findByColumn(String column, Serializable value) {
          Select<E> select = commandFactory.select();
          select.equal(column, value);
          return select(select);
     }

     @Override
     public List<E> findByColumn(String column, Collection<? extends Serializable> values) {
          Select<E> select = commandFactory.select();
          select.whereIn(column, values);
          return select(select);
     }

     /**
      * 获取泛型类的类型
      *
      * @return
      */
     public Class<E> entityClass() {
          return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     }

}
