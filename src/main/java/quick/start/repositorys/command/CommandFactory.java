package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

public class CommandFactory<E extends Entity> {

     private EntityMeta<E> meta;

     public CommandFactory(EntityMeta<E> meta) {
          this.meta = meta;
     }

     public Select<E> select() {
          return new Select<>(meta);
     }

     public Update<E> update() {
          return new Update<>(meta);
     }

     public Insert<E> insert() {
          return new Insert<E>(meta);
     }

     public Delete<E> delete() {
          return new Delete<E>(meta);
     }

     public Count<E> count() {
          return new Count<>(meta);
     }

     public E newInstance() {
          try {
               return meta.getEntityClass().newInstance();
          } catch (Exception ex) {
               ex.printStackTrace();
               return null;
          }
     }

}
