package com.yuanwiequan.common.repository.command;

import com.yuanwiequan.common.entity.Entity;
import com.yuanwiequan.common.entity.EntityMeta;
import com.yuanwiequan.common.repository.condition.Conditions;

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
}
