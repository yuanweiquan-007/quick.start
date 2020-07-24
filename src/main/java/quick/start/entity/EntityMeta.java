package quick.start.entity;

import lombok.Data;
import org.springframework.util.StringUtils;
import quick.start.annotation.*;
import quick.start.util.FieldUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Data
public class EntityMeta<E extends Entity> {

     private Class<E> entityClass;
     private String tableName;
     private String primaryKey;
     private Set<String> insertFields = new HashSet<>();

     private EntityMeta() {
     }

     public static <E extends Entity> EntityMeta<E> of(Class<E> entityClass) {
          EntityMeta<E> meta = new EntityMeta<>();
          meta.setEntityClass(entityClass);
          if (entityClass.isAnnotationPresent(Table.class)) {
               meta.setTableName(entityClass.getAnnotation(Table.class).value());
          }
          if (entityClass.isAnnotationPresent(PrimaryKey.class)) {
               meta.setPrimaryKey(entityClass.getAnnotation(PrimaryKey.class).value());
          }
          if (StringUtils.isEmpty(meta.primaryKey)) {
               parserPrimaryFromField(entityClass, meta);
          }
          meta.parserInsertFields();
          return meta;
     }

     private void parserInsertFields() {
          for (Field field : entityClass.getDeclaredFields()) {
               //@SaveAble(false)、@Generated、未序列化、接口类型不保存
               if (!(FieldUtils.isAnnotationPresent(field, SaveAble.class, x -> !x.value())
                       || FieldUtils.isAnnotationPresent(field, Generated.class)
                       || (!(field.getType() instanceof Serializable))
                       || field.getType().isInterface())) {
                    insertFields.add(FieldUtils.getFieldName(field));
               }
          }
     }

     private static <E extends Entity> void parserPrimaryFromField(Class<E> eClass, EntityMeta<E> meta) {
          for (Field field : eClass.getDeclaredFields()) {
               if (FieldUtils.isAnnotationPresent(field, PrimaryKey.class)) {
                    //@Column注解优先级高
                    if (FieldUtils.isAnnotationPresent(field, Column.class, x -> !StringUtils.isEmpty(x.value()))) {
                         meta.setPrimaryKey(field.getAnnotation(Column.class).value());
                         break;
                    }
                    //再解析@PrimaryKey
                    String primaryKey = field.getAnnotation(PrimaryKey.class).value();
                    if (StringUtils.isEmpty(primaryKey)) {
                         meta.setPrimaryKey(field.getName());
                    } else {
                         meta.setPrimaryKey(primaryKey);
                    }
                    break;
               }
          }
     }

}
