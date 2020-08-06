package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

/**
 * @author yuanweiquan
 */
public class Select<E extends Entity> extends AbstractCommandForEntity<E> {

     public Select(EntityMeta<E> meta) {
          super(meta);
     }

     @Override
     public CommandType commandType() {
          return CommandType.SELECT;
     }

}
