package quick.start.repositorys.support;

import quick.start.repositorys.types.SortType;

/**
 * 排序属性
 */
public class SortAttribute {

     private String field;
     private SortType type;

     private SortAttribute(String field, SortType type) {
          this.field = field;
          this.type = type;
     }

     public static final SortAttribute of(String field, SortType type) {
          return new SortAttribute(field, type);
     }

}