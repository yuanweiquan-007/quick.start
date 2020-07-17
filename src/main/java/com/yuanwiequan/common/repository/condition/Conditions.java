package com.yuanwiequan.common.repository.condition;

import com.yuanwiequan.common.repository.support.ColumnSupport;
import com.yuanwiequan.common.repository.support.PageSupport;
import com.yuanwiequan.common.repository.support.SortAttribute;
import com.yuanwiequan.common.repository.support.SortSupport;
import com.yuanwiequan.common.repository.types.ConditionType;
import com.yuanwiequan.common.repository.types.SortType;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

public class Conditions implements ConditionSupport, SortSupport, PageSupport, ColumnSupport {

     protected Integer pageSize;
     protected Integer pageNumber;
     protected Set<String> columnes = new HashSet<>();
     protected List<SortAttribute> sorts = new ArrayList<>();
     protected List<ConditionAttribute> conditions = new ArrayList<>();

     @Override
     public Conditions where(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.EQUAL, value));
          return this;
     }

     @Override
     public Conditions equal(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.EQUAL, value));
          return this;
     }

     @Override
     public Conditions notEqual(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.NOT_EQUAL, value));
          return this;
     }

     @Override
     public Conditions greaterThen(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.GREATER_THEN, value));
          return this;
     }

     @Override
     public Conditions greaterThenOrEqual(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.GREATER_THEN_OR_EQUAL, value));
          return this;
     }

     @Override
     public Conditions lessThen(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.LESS_THEN, value));
          return this;
     }

     @Override
     public Conditions lessThenOrEqual(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.LESS_THEN_OR_EQUAL, value));
          return this;
     }

     @Override
     public Conditions whereIn(String field, Collection<? extends Serializable> values) {
          conditions.add(ConditionAttribute.of(field, ConditionType.IN, values));
          return this;
     }

     @Override
     public Conditions asc(String field) {
          sorts.add(SortAttribute.of(field, SortType.ASC));
          return this;
     }

     @Override
     public Conditions desc(String field) {
          sorts.add(SortAttribute.of(field, SortType.DESC));
          return this;
     }

     @Override
     public Conditions limit(Integer pageNumber) {
          this.pageSize = 1;
          this.pageNumber = pageNumber;
          return this;
     }

     @Override
     public Conditions limit(Integer pageSize, Integer pageNumber) {
          this.pageSize = pageSize;
          this.pageNumber = pageNumber;
          return this;
     }

     @Override
     public Conditions columns(String... columns) {
          if (CollectionUtils.isEmpty(columnes)) {
               for (String column : columns) {
                    this.columnes.add(column);
               }
          }
          return this;
     }

     public Set<String> getColumnes() {
          return columnes;
     }

     public Integer getPageSize() {
          return pageSize;
     }

     public Integer getPageNumber() {
          return pageNumber;
     }

     public List<SortAttribute> getSorts() {
          return sorts;
     }

     public List<ConditionAttribute> getConditions() {
          return conditions;
     }
}
