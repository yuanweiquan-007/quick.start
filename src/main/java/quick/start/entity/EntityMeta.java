package quick.start.entity;

import lombok.Data;
import org.springframework.util.StringUtils;
import quick.start.annotation.*;
import quick.start.util.AnnotationUtils;
import quick.start.util.FieldUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yuanweiquan
 */
@Data
public class EntityMeta<E extends Entity> {

     /**
      * 对象类
      */
     private Class<E> entityClass;
     /**
      * 表名
      */
     private String tableName;
     /**
      * 主键
      */
     private String primaryKey;
     /**
      * 插入的字段
      */
     private Set<String> insertFields = new HashSet<>();
     /**
      * @Column注解的字段映射：别名 -> 字段名
      */
     private Map<String, String> columnMapping = new HashMap<>();
     /**
      * @Column注解的字段映射：字段名 -> 别名
      */
     private Map<String, String> fieldMapping = new HashMap<>();

     private EntityMeta() {
     }

     public static <E extends Entity> EntityMeta<E> of(Class<E> entityClass) {
          EntityMeta<E> meta = new EntityMeta<>();
          meta.setEntityClass(entityClass);
          AnnotationUtils.isAnnotationPresent(entityClass, Table.class, x -> meta.setTableName(x.value()));
          AnnotationUtils.isAnnotationPresent(entityClass, PrimaryKey.class, x -> meta.setPrimaryKey(x.value()));
          //如果没有@Table注解，默认使用class的名字作为表名
          if (StringUtils.isEmpty(meta.tableName)) {
               meta.setTableName(entityClass.getSimpleName());
          }
          if (StringUtils.isEmpty(meta.primaryKey)) {
               parserPrimaryFromField(entityClass, meta);
          }
          meta.parserInsertFields();
          meta.parserColumnMapping(entityClass);
          return meta;
     }

     private void parserColumnMapping(Class<E> entityClass) {
          for (Field field : entityClass.getDeclaredFields()) {
               if (AnnotationUtils.isAnnotationPresent(field, Column.class, x -> !StringUtils.isEmpty(x.value()))) {
                    String columnValue = AnnotationUtils.getAnnotationAttribute(field.getAnnotation(Column.class), x -> x.value());
                    columnMapping.put(columnValue, field.getName());
                    fieldMapping.put(field.getName(), columnValue);
               }
          }
     }

     private void parserInsertFields() {
          for (Field field : entityClass.getDeclaredFields()) {
               //@SaveAble(false)、@Generated、未序列化、接口类型不保存
               if (!(AnnotationUtils.isAnnotationPresent(field, SaveAble.class, x -> !x.value())
                       || AnnotationUtils.isAnnotationPresent(field, Generated.class)
                       || (!(field.getType() instanceof Serializable))
                       || field.getType().isInterface())) {
                    insertFields.add(FieldUtils.getFieldName(field));
               }
          }
     }

     private static <E extends Entity> void parserPrimaryFromField(Class<E> eClass, EntityMeta<E> meta) {
          for (Field field : eClass.getDeclaredFields()) {
               if (AnnotationUtils.isAnnotationPresent(field, PrimaryKey.class)) {
                    //@Column注解优先级高
                    if (AnnotationUtils.isAnnotationPresent(field, Column.class, x -> !StringUtils.isEmpty(x.value()))) {
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
