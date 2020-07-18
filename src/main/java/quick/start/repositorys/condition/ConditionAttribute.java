package quick.start.repositorys.condition;

import quick.start.repositorys.types.ConditionType;
import lombok.Data;

@Data
public class ConditionAttribute {

     //条件属性
     private String field;
     //查询条件类型
     private ConditionType type;
     //查询条件值
     private Object value;

     private ConditionAttribute(String field, ConditionType type, Object value) {
          this.field = field;
          this.type = type;
          this.value = value;
     }

     public static final ConditionAttribute of(String field, ConditionType type, Object value) {
          return new ConditionAttribute(field, type, value);
     }

}
