package quick.start.repository;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;

public abstract class DefaultAbstractRepository<E extends Entity> implements Repository<E> {

     protected EntityMeta entityMeta;
     protected Logger logger = LoggerFactory.getLogger(getClass());

     public DefaultAbstractRepository() {
          initEntityMeta();
     }

     /**
      * 获取泛型类的类型
      *
      * @return
      */
     protected Class<E> entityClass() {
          return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     }

     protected void checkPrimaryKey() {
          Assert.notNull(entityMeta.getPrimaryKey(), "primaryKey未设置#可以使用@PrimaryKey注解来设置");
     }

     protected void checkTableName() {
          Assert.notNull(entityMeta.getTableName(), "tableName未设置#可以通过注解@Table注解来设置");
     }

     protected void checkTableAndPrimaryKey() {
          checkTableName();
          checkPrimaryKey();
     }

     protected void initEntityMeta() {
          entityMeta = EntityMeta.of(entityClass());
     }

}
