package quick.start.repository.command;

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
     protected Class<E> entityClass() {
          return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     }

     public abstract CommandType commandType();

     public EntityMeta<E> getMeta() {
          return meta;
     }

     public String getPrimaryKey() {
          return meta.getPrimaryKey();
     }

}
