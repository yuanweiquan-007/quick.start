package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

/**
 * @author yuanweiquan
 */
public class Update<E extends Entity> extends AbstractCommandForEntity<E> {

     public Update(EntityMeta<E> meta) {
          super(meta);
     }

     @Override
     public CommandType commandType() {
          return CommandType.UPDATE;
     }

}
