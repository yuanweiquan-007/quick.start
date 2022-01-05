package quick.start.repositorys.command;

import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;

import java.util.List;

/**
 * @author yuanweiquan
 */
public class Insert<E extends Entity> extends AbstractCommandForEntity<E> {

     /**
      * 插入时需要知道对象属性值
      */
     private List<E> values;

     /**
      * 默认构造函数
      *
      * @param meta 元数据
      */
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
