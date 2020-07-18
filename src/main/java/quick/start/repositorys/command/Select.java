package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

public class Select<E extends Entity> extends CommandForEntity<E> {

     public Select(EntityMeta<E> meta) {
          super(meta);
     }

     @Override
     public CommandType commandType() {
          return CommandType.SELECT;
     }

}
