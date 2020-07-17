package quick.start.repository.command;

import quick.start.entity.Entity;

public class Select<E extends Entity> extends CommandForEntity<E> {

     @Override
     public CommandType commandType() {
          return CommandType.SELECT;
     }

}
