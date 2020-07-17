package quick.start.repository.condition;

import java.io.Serializable;
import java.util.Collection;

public interface ConditionSupport {

     public ConditionSupport where(String field, Object value);

     public ConditionSupport equal(String field, Object value);

     public ConditionSupport notEqual(String field, Object value);

     public ConditionSupport greaterThen(String field, Object value);

     public ConditionSupport greaterThenOrEqual(String field, Object value);

     public ConditionSupport lessThen(String field, Object value);

     public ConditionSupport lessThenOrEqual(String field, Object value);

     public ConditionSupport whereIn(String field, Collection<? extends Serializable> values);

}
