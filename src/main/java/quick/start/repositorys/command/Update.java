package quick.start.repositorys.command;

import org.springframework.util.CollectionUtils;
import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

import java.util.Map;

public class Update<E extends Entity> extends CommandForEntity<E> {

     public Update(EntityMeta<E> meta) {
          super(meta);
     }

     public Update from(Map<String, Object> map) {
          if (!CollectionUtils.isEmpty(map)) {
               map.forEach((key, value) -> {
                    if (!key.equals(primaryKey())) {//主键不允许更新
                         this.set(key, value);
                    }
               });
          }
          return this;
     }

     @Override
     public CommandType commandType() {
          return CommandType.UPDATE;
     }

}
