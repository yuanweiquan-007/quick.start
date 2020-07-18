package quick.start.repositorys;

import quick.start.collection.Paginator;
import quick.start.entity.Entity;
import quick.start.repositorys.condition.Conditions;

import java.util.List;

public interface Repository<E extends Entity> extends Writeable<E>, Readable<E> {

     public List<E> find(Conditions conditions);

     public Paginator<E> find(Conditions conditions, Integer pageSize, Integer pageNumber);

}
