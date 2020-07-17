package com.yuanwiequan.common.repository.jdbc.types;

import com.yuanwiequan.common.repository.types.SortType;

public enum JdbcSortType {

     ASC(SortType.ASC, "asc"),
     DESC(SortType.DESC, "desc");

     private String value;
     private SortType type;

     private JdbcSortType(SortType type, String value) {
          this.type = type;
          this.value = value;
     }

     public static String getValue(SortType type) {
          for (JdbcSortType sortType : JdbcSortType.values()) {
               if (sortType.type.equals(type)) {
                    return sortType.value;
               }
          }
          return "";
     }

}
