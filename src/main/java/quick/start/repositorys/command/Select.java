package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

/**
 * @author yuanweiquan
 */
public class Select<E extends Entity> extends AbstractCommandForEntity<E> {

     /**
      * 默认构造函数
      *
      * @param meta 元数据
      */
     public Select(EntityMeta<E> meta) {
          super(meta);
     }

     @Override
     public CommandType commandType() {
          return CommandType.SELECT;
     }

}
