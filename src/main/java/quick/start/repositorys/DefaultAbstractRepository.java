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
          Assert.notNull(id, "查询时主键值为空");
          return count(
                  (Count) commandFactory
                          .count()
                          .checkPrimaryKey()
                          .equal(id)
          ) > 0;
     }

     @Override
     public E findById(Serializable id) {
          Assert.notNull(id, "查询时主键值为空");
          List<E> entitys = select(
                  (Select) commandFactory
                          .select()
                          .checkPrimaryKey()
                          .equal(id)
          );
          if (entitys.size() > 1) {
               throw new UniqueException("根据主键查询出了多条数据");
          }
          return CollectionUtils.isEmpty(entitys) ? commandFactory.newInstance() : entitys.get(0);
     }

     @Override
     public List<E> findByIds(Collection<? extends Serializable> ids) {
          Assert.notEmpty(ids, "查询主键值为空");
          return select(
                  (Select) commandFactory
                          .select()
                          .checkPrimaryKey()
                          .whereIn(ids)
          );
     }

     @Override
     public List<E> find() {
          return select(commandFactory.select());
     }

     @Override
     public List<E> findByColumn(String column, Serializable value) {
          return select(
                  (Select) commandFactory
                          .select()
                          .equal(column, value)
          );
     }

     @Override
     public List<E> findByColumn(String column, Collection<? extends Serializable> values) {
          return select(
                  (Select) commandFactory
                          .select()
                          .whereIn(column, values)
          );
     }

     @Override
     public List<E> find(Conditions conditions) {
          return select(
                  (Select) commandFactory
                          .select()
                          .of(conditions)
          );
     }

     @Override
     public Paginator<E> findByPage(Conditions conditions, Integer pageSize, Integer pageNumber) {
          if (pageNumber < 0) {
               pageNumber = 1;
          }
          Paginator<E> paginator = new Paginator<>(pageSize, pageNumber);
          Integer total = count((Count) commandFactory.count().of(conditions));
          paginator.setTotal(total);
          if (total > 0) {
               paginator.setResults(select((Select) commandFactory.select().of(conditions).limit(pageSize, pageNumber)));
          }
          return paginator;
     }


     @Override
     public Integer delete(Serializable id) {
          Assert.notNull(id, "删除主键值为空");
          return delete(
                  (Delete) commandFactory
                          .delete()
                          .checkPrimaryKey()
                          .equal(id)
          );
     }

     @Override
     public Integer delete(List<? extends Serializable> ids) {
          Assert.notEmpty(ids, "删除主键值为空");
          return delete(
                  (Delete) commandFactory
                          .delete()
                          .checkPrimaryKey()
                          .whereIn(ids)
          );
     }

     @Override
     public Integer delete(String column, Collection<? extends Serializable> values) {
          return delete(
                  (Delete) commandFactory
                          .delete()
                          .whereIn(column, values)
          );
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
          if (entitys.size() > 1) {
               throw new UniqueException("根据主键查询出了多条数据");
          }
          E oldEntity = entitys.get(0);
          Map<String, Object> differents = BeanUtils.differents(oldEntity, entity);
          if (CollectionUtils.isEmpty(differents)) {
               return 0;
          }
          return update(
                  (Update) commandFactory
                          .update()
                          .checkPrimaryKey()
                          .from(differents)
                          .equal(primaryKeyValue)
          );
     }

     @Override
     public Integer update(String id, String key, Object value) {
          Assert.notNull(id, "修改主键值为空");
          return update(
                  (Update) commandFactory
                          .update()
                          .checkPrimaryKey()
                          .set(key, value)
                          .equal(id)
          );
     }

     @Override
     public Integer update(List<? extends Serializable> ids, Map<String, Object> data) {
          Assert.notEmpty(ids, "修改主键值为空");
          return update(
                  (Update) commandFactory
                          .update()
                          .checkPrimaryKey()
                          .from(data)
                          .whereIn(ids)
          );
     }

     @Override
     public Integer update(String id, Map<String, Object> data) {
          Assert.notNull(id, "修改主键值为空");
          return update(
                  (Update) commandFactory
                          .update()
                          .checkPrimaryKey()
                          .from(data)
                          .equal(id)
          );
     }

     @Override
     public Integer update(List<? extends Serializable> ids, String key, Object value) {
          Assert.notEmpty(ids, "修改主键值为空");
          return update(
                  (Update) commandFactory
                          .update()
                          .checkPrimaryKey()
                          .set(key, value)
                          .whereIn(ids)
          );
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
