package quick.start.repository.command;

import org.springframework.util.Assert;
import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;
import quick.start.repository.condition.Conditions;

import java.lang.reflect.ParameterizedType;

public abstract class CommandForEntity<E extends Entity> extends Conditions {

     protected EntityMeta<E> meta;

     public CommandForEntity() {
          this.meta = EntityMeta.of(entityClass());
     }

     /**
      * 获取泛型类的类型
      *
      * @return
      */
     public Class<E> entityClass() {
          return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     }

     public abstract CommandType commandType();

     public EntityMeta<E> getMeta() {
          return meta;
     }

     public String primaryKey() {
          return meta.getPrimaryKey();
     }

     public CommandForEntity checkPrimaryKey() {
          Assert.notNull(meta.getPrimaryKey(), "primaryKey未设置#可以使用@PrimaryKey注解来设置");
          return this;
     }

     public CommandForEntity checkTableName() {
          Assert.notNull(meta.getTableName(), "tableName未设置#可以通过注解@Table注解来设置");
          return this;
     }

     public CommandForEntity checkPrimaryKeyAndTableName() {
          checkTableName();
          checkPrimaryKey();
          return this;
     }

}
