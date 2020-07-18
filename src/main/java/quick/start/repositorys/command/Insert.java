package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

public class Insert<E extends Entity> extends CommandForEntity<E> {

     public Insert(EntityMeta<E> meta) {
          super(meta);
     }

     @Override
     public CommandType commandType() {
          return CommandType.INSERT;
     }

}
