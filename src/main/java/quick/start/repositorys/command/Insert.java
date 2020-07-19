package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

import java.util.List;

public class Insert<E extends Entity> extends CommandForEntity<E> {

     //插入时需要知道对象属性值
     private List<E> values;

     public Insert(EntityMeta<E> meta) {
          super(meta);
     }

     public List<E> getValues() {
          return values;
     }

     public Insert setValues(List<E> values) {
          this.values = values;
          return this;
     }

     @Override
     public CommandType commandType() {
          return CommandType.INSERT;
     }

}
