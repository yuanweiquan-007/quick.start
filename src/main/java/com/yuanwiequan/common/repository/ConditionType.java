package com.yuanwiequan.common.repository;

/**
 * 条件类型
 */
public enum ConditionType {

     EQUAL("="),
     NOT_EQUAL("!="),
     LESS_THEN("<"),
     LESS_THEN_OR_EQUAL("<="),
     GREATER_THEN(">"),
     GREATER_THEN_OR_EQUAL(">="),
     IN("in");

     private String value;

     ConditionType(String value) {
          this.value = value;
     }

     public String getValue() {
          return value;
     }
}
