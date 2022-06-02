package quick.start.repositorys;

import quick.start.entity.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 可写Dao接口
 *
 * @author yuanweiquan
 */
public interface Writeable<E extends Entity> {

     /**
      * 写入实体
      *
      * @param entity 实体
      * @return 影响的条数
      */
     Integer insert(E entity);

     /**
      * 写入多个实体
      *
      * @param entitys 实体
      * @return 影响的条数
      */
     Integer insert(List<E> entitys);

     /**
      * 更新实体
      *
      * @param entity 实体
      * @return 影响的条数
      */
     Integer update(E entity);

     /**
      * 按照指定主键更新多个字段
      *
      * @param ids  主键值
      * @param data 更新数据
      * @return
      */
     Integer update(List<? extends Serializable> ids, Map<String, Object> data);

     /**
      * 按照指定主键更新多个字段
      *
      * @param id   主键
      * @param data 更新数据
      * @return 影响的条数
      */
     Integer update(Serializable id, Map<String, Object> data);

     /**
      * 按照指定主键更新字段
      *
      * @param id    主键值
      * @param key   更新的字段名
      * @param value 值
      * @return 影响的条数
      */
     Integer update(Serializable id, String key, Object value);

     /**
      * 按照指定主键更新字段
      *
      * @param ids   主键值
      * @param key   更新的字段名
      * @param value 值
      * @return 影响的条数
      */
     Integer update(List<? extends Serializable> ids, String key, Object value);

     /**
      * 删除
      *
      * @param id
      * @return
      */
     Integer delete(Serializable id);

     /**
      * 删除多条记录
      *
      * @param ids 主键值
      * @return 影响的条数
      */
     Integer delete(List<? extends Serializable> ids);

     /**
      * 根据字段删除
      *
      * @param column 字段
      * @param values 值
      * @return 影响的条数
      */
     Integer delete(String column, Collection<? extends Serializable> values);

}