package quick.start.entity;

import lombok.Data;
import org.springframework.util.StringUtils;
import quick.start.annotation.Generated;
import quick.start.annotation.PrimaryKey;
import quick.start.annotation.SaveAble;
import quick.start.annotation.Table;

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
          Field[] fields = entityClass.getDeclaredFields();
          for (Field field : fields) {
               //@SaveAble(false)
               if (field.isAnnotationPresent(SaveAble.class) && (false == field.getAnnotation(SaveAble.class).value())) {
                    continue;
               }
               //@Generated,自增不保存
               if (field.isAnnotationPresent(Generated.class)) {
                    continue;
               }
               //其他条件
               if (!(field.getType() instanceof Serializable) || field.getType().isInterface()) {
                    continue;
               }
               insertFields.add(field.getName());
          }
     }

     private static <E extends Entity> void parserPrimaryFromField(Class<E> eClass, EntityMeta<E> meta) {
          Field[] declaredFields = eClass.getDeclaredFields();
          for (Field field : declaredFields) {
               if (field.isAnnotationPresent(PrimaryKey.class)) {
                    PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                    if (StringUtils.isEmpty(primaryKey.value())) {
                         meta.setPrimaryKey(field.getName());
                         break;
                    }
               }
          }
     }

}
