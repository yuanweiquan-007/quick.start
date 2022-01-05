package quick.start.repositorys.jdbc.types;

import quick.start.constant.CommonConstant;
import quick.start.repositorys.types.ConditionType;

/**
 * @author yuanweiquan
 */
public enum JdbcConditionType {
     /**
      * 等于
      */
     EQUAL(ConditionType.EQUAL, "="),
     /**
      * 不等于
      */
     NOT_EQUAL(ConditionType.NOT_EQUAL, "!="),
     /**
      * 小于
      */
     LESS_THEN(ConditionType.LESS_THEN, "<"),
     /**
      * 小于等于
      */
     LESS_THEN_OR_EQUAL(ConditionType.LESS_THEN_OR_EQUAL, "<="),
     /**
      * 大于
      */
     GREATER_THEN(ConditionType.GREATER_THEN, ">"),
     /**
      * 大于等于
      */
     GREATER_THEN_OR_EQUAL(ConditionType.GREATER_THEN_OR_EQUAL, ">="),
     /**
      * in
      */
     IN(ConditionType.IN, "in"),
     /**
      * like
      */
     LIKE(ConditionType.LIKE, "like");

     private String value;
     private ConditionType type;

     private JdbcConditionType(ConditionType type, String value) {
          this.type = type;
          this.value = value;
     }

     /**
      * 得到值
      *
      * @param type 类型
      * @return {@link String}
      */
     public static String getValue(ConditionType type) {
          for (JdbcConditionType jdbcConditionType : JdbcConditionType.values()) {
               if (jdbcConditionType.type.equals(type)) {
                    return jdbcConditionType.value;
               }
          }
          return CommonConstant.NULL;
     }

}
