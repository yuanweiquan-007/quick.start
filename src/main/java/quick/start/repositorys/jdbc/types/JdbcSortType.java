package quick.start.repositorys.jdbc.types;

import quick.start.repositorys.types.SortType;

/**
 * @author yuanweiquan
 */
public enum JdbcSortType {
     /**
      * 升序
      */
     ASC(SortType.ASC, "asc"),
     /**
      * 降序
      */
     DESC(SortType.DESC, "desc");

     private String value;
     private SortType type;

     /**
      * jdbc类型排序
      *
      * @param type  类型
      * @param value 值
      */
     private JdbcSortType(SortType type, String value) {
          this.type = type;
          this.value = value;
     }

     /**
      * 得到值
      *
      * @param type 类型
      * @return {@link String}
      */
     public static String getValue(SortType type) {
          for (JdbcSortType sortType : JdbcSortType.values()) {
               if (sortType.type.equals(type)) {
                    return sortType.value;
               }
          }
          return "";
     }

}
