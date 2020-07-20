package quick.start.repositorys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import quick.start.collection.Paginator;
import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;
import quick.start.exception.NotFoundExceptin;
import quick.start.exception.UniqueException;
import quick.start.parser.CommandParser;
import quick.start.repositorys.command.*;
import quick.start.repositorys.condition.Conditions;
import quick.start.util.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class DefaultAbstractRepository<E extends Entity, P extends CommandParser> implements Repository<E> {

     protected CommandParser commandParser;
     protected CommandFactory<E> commandFactory;
     protected Logger logger = LoggerFactory.getLogger(getClass());

     public DefaultAbstractRepository() {
          commandFactory = new CommandFactory<>(EntityMeta.of(entityClass()));
     }

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

     @Override
     public List<E> find(Conditions conditions) {
          Select<E> select = commandFactory.select();
          select.of(conditions);
          return select(select);
     }

     @Override
     public Paginator<E> findByPage(Conditions conditions, Integer pageSize, Integer pageNumber) {
          if (pageNumber < 0) {
               pageNumber = 1;
          }
          Paginator<E> paginator = new Paginator<>(pageSize, pageNumber);
          Count<E> count = commandFactory.count();
          count.of(conditions);
          Integer total = count(count);
          paginator.setTotal(total);
          if (total > 0) {
               Select<E> select = commandFactory.select();
               select.of(conditions).limit(pageSize, pageNumber);
               paginator.setResults(select(select));
          }
          return paginator;
     }


     @Override
     public Integer delete(Serializable id) {
          Assert.notNull(id, "删除主键值为空");
          Delete<E> delete = commandFactory.delete();
          delete.where(delete.primaryKey(), id);
          return delete(delete);
     }

     @Override
     public Integer delete(List<? extends Serializable> ids) {
          Assert.notEmpty(ids, "删除主键值为空");
          Delete<E> delete = commandFactory.delete();
          delete.whereIn(delete.primaryKey(), ids);
          return delete(delete);
     }

     @Override
     public Integer delete(String column, Collection<? extends Serializable> values) {
          Delete<E> delete = commandFactory.delete();
          delete.whereIn(column, values);
          return delete(delete);
     }

     @Override
     public Integer insert(E entity) {
          return insert(commandFactory.insert().setValues(Arrays.asList(entity)));
     }

     @Override
     public Integer insert(List<E> entitys) {
          return insert(commandFactory.insert().setValues(entitys));
     }

     @Override
     public Integer update(String id, String key, Object value) {
          Assert.notNull(id, "修改主键值为空");
          Update<E> update = commandFactory.update();
          update.set(key, value).where(update.primaryKey(), id);
          return update(update);
     }

     @Override
     public Integer update(E entity) {
          Map<String, Object> map = BeanUtils.parserAttributeValues(entity);
          Select<E> select = commandFactory.select();
          String primaryKey = select.primaryKey();
          Object primaryKeyValue = map.get(primaryKey);
          if (ObjectUtils.isEmpty(primaryKeyValue)) {
               throw new IllegalArgumentException("更新时未发现主键的值");
          }
          select.where(primaryKey, primaryKeyValue);
          List<E> entitys = select(select);
          if (CollectionUtils.isEmpty(entitys)) {
               throw new NotFoundExceptin("未发现记录");
          }

          E oldEntity = entitys.get(0);
          Map<String, Object> differents = BeanUtils.differents(oldEntity, entity);
          Update<E> update = commandFactory.update();
          update.from(differents).where(primaryKey, primaryKeyValue);
          return update(update);
     }

     @Override
     public Integer update(List<? extends Serializable> ids, Map<String, Object> data) {
          Assert.notEmpty(ids, "修改主键值为空");
          Update<E> update = commandFactory.update();
          update.from(data).whereIn(update.primaryKey(), ids);
          return update(update);
     }

     @Override
     public Integer update(String id, Map<String, Object> data) {
          Assert.notNull(id, "修改主键值为空");
          Update<E> update = commandFactory.update();
          update.from(data).where(update.primaryKey(), id);
          return update(update);
     }

     @Override
     public Integer update(List<? extends Serializable> ids, String key, Object value) {
          Assert.notEmpty(ids, "修改主键值为空");
          Update<E> update = commandFactory.update();
          update.set(key, value).whereIn(update.primaryKey(), ids);
          return update(update);
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
