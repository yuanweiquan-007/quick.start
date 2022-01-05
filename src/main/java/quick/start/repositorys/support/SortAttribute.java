package quick.start.repositorys.support;

import lombok.Data;
import quick.start.repositorys.types.SortType;

/**
 * 排序属性
 * @author yuanweiquan
 */
@Data
public class SortAttribute {

     private String field;
     private SortType type;

     /**
      * 类属性构造函数
      *
      * @param field 字段
      * @param type  类型
      */
     private SortAttribute(String field, SortType type) {
          this.field = field;
          this.type = type;
     }

     /**
      * @param field 字段
      * @param type  类型
      * @return {@link SortAttribute}
      */
     public static final SortAttribute of(String field, SortType type) {
          return new SortAttribute(field, type);
     }

}
