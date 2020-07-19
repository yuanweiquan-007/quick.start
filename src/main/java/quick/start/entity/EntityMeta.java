package quick.start.entity;

import quick.start.annotation.Generated;
import quick.start.annotation.PrimaryKey;
import quick.start.annotation.SaveAble;
import quick.start.annotation.Table;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

@Data
public class EntityMeta<E extends Entity> {

     private Class<E> eClass;
     private String tableName;
     private String primaryKey;
     private Set<String> insertFields;

     private EntityMeta() {
     }

     public static <E extends Entity> EntityMeta<E> of(Class<E> eClass) {
          EntityMeta<E> meta = new EntityMeta<>();
          meta.setEClass(eClass);
          if (eClass.isAnnotationPresent(Table.class)) {
               meta.setTableName(eClass.getAnnotation(Table.class).value());
          }
          if (eClass.isAnnotationPresent(PrimaryKey.class)) {
               meta.setPrimaryKey(eClass.getAnnotation(PrimaryKey.class).value());
          }
          if (StringUtils.isEmpty(meta.primaryKey)) {
               parserPrimaryFromField(eClass, meta);
          }
          meta.parserInsertFields();
          return meta;
     }

     private void parserInsertFields() {
          Field[] fields = eClass.getDeclaredFields();
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

     public E newInstance() {
          try {
               return eClass.newInstance();
          } catch (Exception ex) {
               ex.printStackTrace();
               return null;
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
