package com.yuanwiequan.common.repository;

import com.yuanwiequan.common.entity.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 可读Dao接口
 */
public interface Readable<E extends Entity> {

     /**
      * 根据主键值查找
      *
      * @param id 主键值
      * @return
      */
     E findById(Serializable id);

     /**
      * 查找多个
      *
      * @param ids 主键值
      * @return
      */
     List<E> findByIds(Collection<? extends Serializable> ids);

     /**
      * 根据字段查找
      *
      * @param column 字段名
      * @param value  字段值
      * @return
      */
     List<E> findByColumn(String column, Serializable value);

     /**
      * 根据字段查找
      *
      * @param column 字段名
      * @param values 字段值
      * @return
      */
     List<E> findByColumn(String column, Collection<? extends Serializable> values);

     /**
      * 查找
      *
      * @return
      */
     List<E> find();

     /**
      * 判断多重主键是否存在
      *
      * @param id
      * @return
      */
     boolean has(Serializable id);

}