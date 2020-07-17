package quick.start.repository.command;

import quick.start.entity.Entity;

public class CommandFactory<E extends Entity> {

     public Select<E> select() {
          return new Select<>();
     }

     public Update<E> update() {
          return new Update<>();
     }

     public Insert<E> insert() {
          return new Insert<E>();
     }

     public Delete<E> delete() {
          return new Delete<E>();
     }

     public Count<E> count() {
          return new Count<>();
     }

}
