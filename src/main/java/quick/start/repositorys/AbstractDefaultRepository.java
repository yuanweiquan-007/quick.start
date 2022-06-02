package quick.start.repositorys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import quick.start.collection.Paginator;
import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;
import quick.start.exception.NotFoundException;
import quick.start.exception.UniqueException;
import quick.start.parser.AbstractCommandParser;
import quick.start.repositorys.command.*;
import quick.start.repositorys.condition.Conditions;
import quick.start.util.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @author yuanweiquan
 */
public abstract class AbstractDefaultRepository<E extends Entity, P extends AbstractCommandParser> implements Repository<E> {

    protected AbstractCommandParser commandParser;
    protected CommandFactory<E> commandFactory;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractDefaultRepository() {
        commandFactory = new CommandFactory<>(EntityMeta.of(entityClass()));
    }

    /**
     * 查询
     *
     * @param select 选择器
     * @return 集合
     */
    protected abstract List<E> select(Select<E> select);

    /**
     * 修改
     *
     * @param update 修改器
     * @return 集合
     */
    protected abstract Integer update(Update<E> update);

    /**
     * 新增
     *
     * @param insert 插入对象
     * @return 影响的条数
     */
    protected abstract Integer insert(Insert<E> insert);

    /**
     * 删除
     *
     * @param delete 删除器
     * @return 影响的条数
     */
    protected abstract Integer delete(Delete<E> delete);

    /**
     * 数
     * 统计
     *
     * @param count 统计查询器
     * @return 影响的条数
     */
    protected abstract Integer count(Count<E> count);

    /**
     * 有
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean has(Serializable id) {
        Assert.notNull(id, "查询时主键值为空");
        return count(
                (Count) commandFactory
                        .count()
                        .checkPrimaryKey()
                        .equal(id)
        ) > 0;
    }

    /**
     * 发现通过id
     *
     * @param id id
     * @return {@link E}
     */
    @Override
    public E findById(Serializable id) {
        Assert.notNull(id, "查询时主键值为空");
        List<E> entitys = select(
                (Select) commandFactory
                        .select()
                        .checkPrimaryKey()
                        .equal(id)
        );
        if (entitys.size() > 1) {
            throw new UniqueException("根据主键查询出了多条数据");
        }
        return CollectionUtils.isEmpty(entitys) ? commandFactory.newInstance() : entitys.get(0);
    }

    /**
     * 发现由ids
     *
     * @param ids id
     * @return {@link List}<{@link E}>
     */
    @Override
    public List<E> findByIds(Collection<? extends Serializable> ids) {
        Assert.notEmpty(ids, "查询主键值为空");
        return select(
                (Select) commandFactory
                        .select()
                        .checkPrimaryKey()
                        .whereIn(ids)
        );
    }

    /**
     * 找到
     *
     * @return {@link List}<{@link E}>
     */
    @Override
    public List<E> find() {
        return select(commandFactory.select());
    }

    /**
     * 找到了列
     *
     * @param column 列
     * @param value  价值
     * @return {@link List}<{@link E}>
     */
    @Override
    public List<E> findByColumn(String column, Serializable value) {
        return select(
                (Select) commandFactory
                        .select()
                        .equal(column, value)
        );
    }

    /**
     * 找到了列
     *
     * @param column 列
     * @param values 值
     * @return {@link List}<{@link E}>
     */
    @Override
    public List<E> findByColumn(String column, Collection<? extends Serializable> values) {
        return select(
                (Select) commandFactory
                        .select()
                        .whereIn(column, values)
        );
    }

    /**
     * 找到
     *
     * @param conditions 条件
     * @return {@link List}<{@link E}>
     */
    @Override
    public List<E> find(Conditions conditions) {
        return select(
                (Select) commandFactory
                        .select()
                        .of(conditions)
        );
    }

    /**
     * 找到页面
     *
     * @param conditions 条件
     * @param pageSize   页面大小
     * @param pageNumber 页码
     * @return {@link Paginator}<{@link E}>
     */
    @Override
    public Paginator<E> findByPage(Conditions conditions, Integer pageSize, Integer pageNumber) {
        if (pageNumber < 0) {
            pageNumber = 1;
        }
        Paginator<E> paginator = new Paginator<>(pageSize, pageNumber);
        Integer total = count((Count) commandFactory.count().of(conditions));
        paginator.setTotal(total);
        if (total > 0) {
            paginator.setResults(select((Select) commandFactory.select().of(conditions).limit(pageSize, pageNumber)));
        }
        return paginator;
    }


    /**
     * 删除
     *
     * @param id id
     * @return {@link Integer}
     */
    @Override
    public Integer delete(Serializable id) {
        Assert.notNull(id, "删除主键值为空");
        return delete(
                (Delete) commandFactory
                        .delete()
                        .checkPrimaryKey()
                        .equal(id)
        );
    }

    /**
     * 删除
     *
     * @param ids id
     * @return {@link Integer}
     */
    @Override
    public Integer delete(List<? extends Serializable> ids) {
        Assert.notEmpty(ids, "删除主键值为空");
        return delete(
                (Delete) commandFactory
                        .delete()
                        .checkPrimaryKey()
                        .whereIn(ids)
        );
    }

    /**
     * 删除
     *
     * @param column 列
     * @param values 值
     * @return {@link Integer}
     */
    @Override
    public Integer delete(String column, Collection<? extends Serializable> values) {
        return delete(
                (Delete) commandFactory
                        .delete()
                        .whereIn(column, values)
        );
    }

    /**
     * 插入
     *
     * @param entity 实体
     * @return {@link Integer}
     */
    @Override
    public Integer insert(E entity) {
        return insert(commandFactory.insert().setValues(Arrays.asList(entity)));
    }

    /**
     * 插入
     *
     * @param entitys 实体
     * @return {@link Integer}
     */
    @Override
    public Integer insert(List<E> entitys) {
        return insert(commandFactory.insert().setValues(entitys));
    }

    @Override
    public Integer update(E entity) {
        Select<E> select = commandFactory.select();
        Map<String, Object> map = BeanUtils.parserAttributeValues(entity);
        appendMappingIfNecessary(map, select.getMeta().getColumnMapping());
        String primaryKey = select.primaryKey();
        Object primaryKeyValue = map.get(primaryKey);
        if (ObjectUtils.isEmpty(primaryKeyValue)) {
            throw new IllegalArgumentException("更新时未发现主键的值");
        }
        select.where(primaryKey, primaryKeyValue);
        List<E> entitys = select(select);
        if (CollectionUtils.isEmpty(entitys)) {
            throw new NotFoundException("未发现记录");
        }
        if (entitys.size() > 1) {
            throw new UniqueException("根据主键查询出了多条数据");
        }
        E oldEntity = entitys.get(0);
        Map<String, Object> differents = replaceRealFiledNameIfNecessary(BeanUtils.differents(oldEntity, entity), select.getMeta().getFieldMapping());
        if (CollectionUtils.isEmpty(differents)) {
            return 0;
        }
        return update(
                (Update) commandFactory
                        .update()
                        .checkPrimaryKey()
                        .from(differents)
                        .equal(primaryKeyValue)
        );
    }

    @Override
    public Integer update(Serializable id, String key, Object value) {
        Assert.notNull(id, "修改主键值为空");
        return update(
                (Update) commandFactory
                        .update()
                        .checkPrimaryKey()
                        .set(key, value)
                        .equal(id)
        );
    }

    @Override
    public Integer update(List<? extends Serializable> ids, Map<String, Object> data) {
        Assert.notEmpty(ids, "修改主键值为空");
        return update(
                (Update) commandFactory
                        .update()
                        .checkPrimaryKey()
                        .from(data)
                        .whereIn(ids)
        );
    }

    @Override
    public Integer update(Serializable id, Map<String, Object> data) {
        Assert.notNull(id, "修改主键值为空");
        return update(
                (Update) commandFactory
                        .update()
                        .checkPrimaryKey()
                        .from(data)
                        .equal(id)
        );
    }

    @Override
    public Integer update(List<? extends Serializable> ids, String key, Object value) {
        Assert.notEmpty(ids, "修改主键值为空");
        return update(
                (Update) commandFactory
                        .update()
                        .checkPrimaryKey()
                        .set(key, value)
                        .whereIn(ids)
        );
    }

    protected void appendMappingIfNecessary(List<Map<String, Object>> data, Map<String, String> mapping) {
        if (!CollectionUtils.isEmpty(data) && !CollectionUtils.isEmpty(mapping)) {
            for (Map<String, Object> map : data) {
                appendMappingIfNecessary(map, mapping);
            }
        }
    }

    protected void appendMappingIfNecessary(Map<String, Object> data, Map<String, String> mapping) {
        mapping.forEach((k, v) -> {
            if (data.containsKey(k)) {
                data.put(v, data.get(k));
            }
        });
    }

    protected Map<String, Object> replaceRealFiledNameIfNecessary(Map<String, Object> data, Map<String, String> mapping) {
        if (CollectionUtils.isEmpty(mapping)) {
            return data;
        }

        Map<String, Object> result = new HashMap<>(16);
        data.forEach((k, v) -> {
            result.put(mapping.getOrDefault(k, k), v);
        });
        return result;
    }

    /**
     * 获取泛型类的类型
     *
     * @return
     */
    public Class<E> entityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


}
