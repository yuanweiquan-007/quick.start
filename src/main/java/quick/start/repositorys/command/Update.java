package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

public class Update<E extends Entity> extends CommandForEntity<E> {

     public Update(EntityMeta<E> meta) {
          super(meta);
     }

     @Override
     public CommandType commandType() {
          return CommandType.UPDATE;
     }

}
