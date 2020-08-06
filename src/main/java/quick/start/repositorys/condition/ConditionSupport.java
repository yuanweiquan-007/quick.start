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
      * @param field
      * @param value
      * @return
      */
     public ConditionSupport where(String field, Object value);

     /**
      * 等于
      *
      * @param field
      * @param value
      * @return
      */
     public ConditionSupport equal(String field, Object value);

     /**
      * 不等于
      *
      * @param field
      * @param value
      * @return
      */
     public ConditionSupport notEqual(String field, Object value);

     /**
      * 大于
      *
      * @param field
      * @param value
      * @return
      */
     public ConditionSupport greaterThen(String field, Object value);

     /**
      * 大于等于
      *
      * @param field
      * @param value
      * @return
      */
     public ConditionSupport greaterThenOrEqual(String field, Object value);

     /**
      * 小于
      *
      * @param field
      * @param value
      * @return
      */
     public ConditionSupport lessThen(String field, Object value);

     /**
      * 小于等于
      *
      * @param field
      * @param value
      * @return
      */
     public ConditionSupport lessThenOrEqual(String field, Object value);

     /**
      * like
      *
      * @param field
      * @param value
      * @return
      */
     public ConditionSupport like(String field, Object value);

     /**
      * in
      *
      * @param field
      * @param values
      * @return
      */
     public ConditionSupport whereIn(String field, Collection<? extends Serializable> values);

}
