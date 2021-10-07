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
     * @param conditions 条件
     * @return 集合
     */
    public List<E> find(Conditions conditions);

    /**
     * 分页搜索
     *
     * @param conditions 条件
     * @param pageSize   每页的数量
     * @param pageNumber 第几页
     * @return
     */
    public Paginator<E> findByPage(Conditions conditions, Integer pageSize, Integer pageNumber);

}
