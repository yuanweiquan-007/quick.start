package quick.start.repositorys.condition;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author yuanweiquan
 */
public interface ConditionSupport {

     /**
      * where
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport where(String field, Object value);

     /**
      * 等于
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport equal(String field, Object value);

     /**
      * 不等于
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport notEqual(String field, Object value);

     /**
      * 大于
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport greaterThen(String field, Object value);

     /**
      * 大于等于
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport greaterThenOrEqual(String field, Object value);

     /**
      * 小于
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport lessThen(String field, Object value);

     /**
      * 小于等于
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport lessThenOrEqual(String field, Object value);

     /**
      * like
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport like(String field, Object value);

     /**
      * in
      *
      * @param field 字段
      * @param value 值
      * @return ConditionSupport
      */
     public ConditionSupport whereIn(String field, Collection<? extends Serializable> values);

}
