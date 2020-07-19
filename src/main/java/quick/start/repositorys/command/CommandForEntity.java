package quick.start.repositorys.command;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;
import quick.start.repositorys.condition.Conditions;
import quick.start.repositorys.support.SetAttribute;
import quick.start.repositorys.support.SetSupport;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandForEntity<E extends Entity> extends Conditions implements SetSupport {

     protected EntityMeta<E> meta;
     protected List<SetAttribute> setAttributes = new ArrayList<>();

     public CommandForEntity(EntityMeta<E> meta) {
          this.meta = meta;
     }

     public abstract CommandType commandType();

     public EntityMeta<E> getMeta() {
          return meta;
     }

     public String primaryKey() {
          return meta.getPrimaryKey();
     }

     public CommandForEntity checkPrimaryKey() {
          Assert.notNull(meta.getPrimaryKey(), "primaryKey未设置#可以使用@PrimaryKey注解来设置");
          return this;
     }

     public CommandForEntity checkTableName() {
          Assert.notNull(meta.getTableName(), "tableName未设置#可以通过注解@Table注解来设置");
          return this;
     }

     public CommandForEntity checkPrimaryKeyAndTableName() {
          checkTableName();
          checkPrimaryKey();
          return this;
     }

     public CommandForEntity of(Conditions conditions) {
          if (!ObjectUtils.isEmpty(conditions)) {
               this.pageSize = conditions.getPageSize();
               this.pageNumber = conditions.getPageNumber();
               this.columnes = conditions.getColumnes();
               this.sorts = conditions.getSorts();
               this.conditions = conditions.getConditions();
          }
          return this;
     }

     @Override
     public CommandForEntity set(String key, Object value) {
          setAttributes.add(new SetAttribute(key, value));
          return this;
     }

     public List<SetAttribute> getSetAttributes() {
          return setAttributes;
     }
}
