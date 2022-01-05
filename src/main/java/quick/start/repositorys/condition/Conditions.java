package quick.start.repositorys.condition;

import org.springframework.util.CollectionUtils;
import quick.start.repositorys.support.ColumnSupport;
import quick.start.repositorys.support.PageSupport;
import quick.start.repositorys.support.SortAttribute;
import quick.start.repositorys.support.SortSupport;
import quick.start.repositorys.types.ConditionType;
import quick.start.repositorys.types.SortType;

import java.io.Serializable;
import java.util.*;

/**
 * @author yuanweiquan
 */
public class Conditions implements ConditionSupport, SortSupport, PageSupport, ColumnSupport {

     /**
      * 每页代销
      */
     protected Integer pageSize;
     /**
      * 当前页
      */
     protected Integer pageNumber;
     /**
      * 字段
      */
     protected Set<String> columnes = new HashSet<>();
     /**
      * 排序
      */
     protected List<SortAttribute> sorts = new ArrayList<>();
     /**
      * 条件
      */
     protected List<ConditionAttribute> conditions = new ArrayList<>();

     /**
      * 条件查询
      *
      * @param field 字段
      * @param value 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions where(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.EQUAL, value));
          return this;
     }

     /**
      * 等
      *
      * @param field 字段
      * @param value 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions equal(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.EQUAL, value));
          return this;
     }

     /**
      * 不等
      *
      * @param field 字段
      * @param value 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions notEqual(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.NOT_EQUAL, value));
          return this;
     }

     /**
      * 大于
      *
      * @param field 字段
      * @param value 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions greaterThen(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.GREATER_THEN, value));
          return this;
     }

     /**
      * 大于或等于
      *
      * @param field 字段
      * @param value 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions greaterThenOrEqual(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.GREATER_THEN_OR_EQUAL, value));
          return this;
     }

     /**
      * 小于
      *
      * @param field 字段
      * @param value 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions lessThen(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.LESS_THEN, value));
          return this;
     }

     /**
      * 小于或等于
      *
      * @param field 字段
      * @param value 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions lessThenOrEqual(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.LESS_THEN_OR_EQUAL, value));
          return this;
     }

     /**
      * 模糊查询
      *
      * @param field 字段
      * @param value 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions like(String field, Object value) {
          conditions.add(ConditionAttribute.of(field, ConditionType.LIKE, value));
          return this;
     }

     /**
      * in查询
      *
      * @param field  字段
      * @param values 值
      * @return {@link Conditions}
      */
     @Override
     public Conditions whereIn(String field, Collection<? extends Serializable> values) {
          conditions.add(ConditionAttribute.of(field, ConditionType.IN, values));
          return this;
     }

     /**
      * asc
      *
      * @param field 场
      * @return {@link Conditions}
      */
     @Override
     public Conditions asc(String field) {
          sorts.add(SortAttribute.of(field, SortType.ASC));
          return this;
     }

     /**
      * desc
      *
      * @param field 场
      * @return {@link Conditions}
      */
     @Override
     public Conditions desc(String field) {
          sorts.add(SortAttribute.of(field, SortType.DESC));
          return this;
     }

     /**
      * 限制
      *
      * @param pageSize 页面大小
      * @return {@link Conditions}
      */
     @Override
     public Conditions limit(Integer pageSize) {
          this.pageNumber = 1;
          this.pageSize = pageSize;
          return this;
     }

     /**
      * 限制
      *
      * @param pageSize   页面大小
      * @param pageNumber 页码
      * @return {@link Conditions}
      */
     @Override
     public Conditions limit(Integer pageSize, Integer pageNumber) {
          this.pageSize = pageSize;
          this.pageNumber = pageNumber;
          return this;
     }

     /**
      * 列
      *
      * @param columns 列
      * @return {@link Conditions}
      */
     @Override
     public Conditions columns(String... columns) {
          if (CollectionUtils.isEmpty(columnes)) {
               for (String column : columns) {
                    this.columnes.add(column);
               }
          }
          return this;
     }

     /**
      * 获得列
      *
      * @return {@link Set}<{@link String}>
      */
     public Set<String> getColumnes() {
          return columnes;
     }

     /**
      * 获取页面大小
      *
      * @return Integer
      */
     public Integer getPageSize() {
          return pageSize;
     }

     /**
      * 页码
      *
      * @return Integer
      */
     public Integer getPageNumber() {
          return pageNumber;
     }

     /**
      * 获得排序
      *
      * @return SortAttribute
      */
     public List<SortAttribute> getSorts() {
          return sorts;
     }


     /**
      * 得到条件
      *
      * @return ConditionAttribute
      */
     public List<ConditionAttribute> getConditions() {
          return conditions;
     }
}
