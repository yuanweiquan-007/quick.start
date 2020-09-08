package quick.start.repositorys.command;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;
import quick.start.repositorys.condition.ConditionAttribute;
import quick.start.repositorys.condition.Conditions;
import quick.start.repositorys.support.SetAttribute;
import quick.start.repositorys.support.SetSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author yuanweiquan
 */
public abstract class AbstractCommandForEntity<E extends Entity> extends Conditions implements SetSupport {

     protected EntityMeta<E> meta;
     protected List<SetAttribute> setAttributes = new ArrayList<>();

     public AbstractCommandForEntity(EntityMeta<E> meta) {
          this.meta = meta;
     }

     /**
      * 命令类型
      *
      * @return
      * @see CommandType
      */
     public abstract CommandType commandType();

     public EntityMeta<E> getMeta() {
          return meta;
     }

     public String primaryKey() {
          return meta.getPrimaryKey();
     }

     public AbstractCommandForEntity checkPrimaryKey() {
          Assert.notNull(meta.getPrimaryKey(), "primaryKey未设置#可以使用@PrimaryKey注解来设置");
          return this;
     }

     /**
      * 默认走主键
      *
      * @param value
      * @return
      */
     public AbstractCommandForEntity equal(Object value) {
          this.equal(primaryKey(), value);
          return this;
     }

     /**
      * 默认走主键
      *
      * @param values
      * @return
      */
     public AbstractCommandForEntity whereIn(Collection<? extends Serializable> values) {
          this.whereIn(primaryKey(), values);
          return this;
     }

     public AbstractCommandForEntity of(Conditions conditions) {
          if (!ObjectUtils.isEmpty(conditions)) {
               this.pageSize = conditions.getPageSize();
               this.pageNumber = conditions.getPageNumber();
               this.columnes = conditions.getColumnes();
               this.sorts = conditions.getSorts();
               this.conditions = conditions.getConditions();
          }
          return this;
     }

     public AbstractCommandForEntity from(Map<String, Object> map) {
          if (!CollectionUtils.isEmpty(map)) {
               map.forEach((key, value) -> {
                    this.set(key, value);
               });
          }
          return this;
     }

     @Override
     public AbstractCommandForEntity set(String key, Object value) {
          Map<String, String> fieldMapping = meta.getFieldMapping();
          if (fieldMapping.containsKey(key)) {
               key = fieldMapping.get(key);
          }
          //主键及自增ID不允许更新
          if (!key.equals(primaryKey()) && !key.equals(meta.getGeneratedKey())) {
               setAttributes.add(new SetAttribute(key, value));
          }
          return this;
     }

     public List<SetAttribute> getSetAttributes() {
          return setAttributes;
     }

     @Override
     public List<ConditionAttribute> getConditions() {
          Map<String, String> fieldMapping = meta.getFieldMapping();
          for (ConditionAttribute condition : this.conditions) {
               if (fieldMapping.containsKey(condition.getField())) {
                    condition.setField(fieldMapping.get(condition.getField()));
               }
          }
          return this.conditions;
     }
}
