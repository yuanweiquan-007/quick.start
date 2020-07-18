package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

public class Delete<E extends Entity> extends CommandForEntity<E> {

     public Delete(EntityMeta<E> meta) {
          super(meta);
     }

     @Override
     public CommandType commandType() {
          return CommandType.DELETE;
     }

}
