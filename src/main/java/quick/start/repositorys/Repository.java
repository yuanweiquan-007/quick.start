package quick.start.repositorys;

import quick.start.collection.Paginator;
import quick.start.entity.Entity;
import quick.start.repositorys.condition.Conditions;

import java.util.List;

/**
 * @author yuanweiquan
 */
public interface Repository<E extends Entity> extends Writeable<E>, Readable<E> {

     /**
      * 根据条件搜索
      *
      * @param conditions
      * @return
      */
     public List<E> find(Conditions conditions);

     /**
      * 分页搜索
      *
      * @param conditions
      * @param pageSize
      * @param pageNumber
      * @return
      */
     public Paginator<E> findByPage(Conditions conditions, Integer pageSize, Integer pageNumber);

}
