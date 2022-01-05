package quick.start.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import quick.start.collection.Paginator;
import quick.start.entity.Entity;
import quick.start.repositorys.condition.Conditions;
import quick.start.repositorys.jdbc.JdbcRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * jdbc默认的抽象类
 */
@Service
public abstract class AbstractDefaultJdbcService<E extends Entity, D extends JdbcRepository<E>> implements ApplicationContextAware {

    private D repository;

    public Boolean has(Serializable id) {
        return repository.has(id);
    }

    /**
     * 新增
     *
     * @param entity 实体对象
     * @return {@link Integer}
     */
    public Integer add(E entity) {
        return repository.insert(entity);
    }

    /**
     * 新增
     *
     * @param entitys 实体
     * @return {@link Integer}
     */
    public Integer add(List<E> entitys) {
        return repository.insert(entitys);
    }

    /**
     * 通过主键查询
     *
     * @param id id
     * @return {@link E}
     */
    public E findById(Serializable id) {
        return repository.findById(id);
    }

    /**
     * 查询
     *
     * @param conditions 条件
     * @return {@link List}
     */
    public List<E> find(Conditions conditions) {
        return repository.find(conditions);
    }

    /**
     * 通过字段查询
     *
     * @param column 字段
     * @param value  值
     * @return {@link List}
     */
    public List<E> findByColumn(String column, Serializable value) {
        return repository.findByColumn(column, value);
    }

    /**
     * 通过字段查询
     *
     * @param column 字段
     * @param values 值
     * @return {@link List}
     */
    public List<E> findByColumn(String column, Collection<? extends Serializable> values) {
        return repository.findByColumn(column, values);
    }

    /**
     * 分页查询
     *
     * @param conditions 条件
     * @param pageSize   每页大小
     * @param pageNumber 当前页数
     * @return {@link Paginator}
     */
    public Paginator<E> findByPage(Conditions conditions, Integer pageSize, Integer pageNumber) {
        return repository.findByPage(conditions, pageSize, pageNumber);
    }

    /**
     * 更新
     *
     * @param entity 实体
     * @return {@link Integer}
     */
    public Integer update(E entity) {
        return repository.update(entity);
    }

    /**
     * 更新
     *
     * @param id    id
     * @param key   关键词
     * @param value 值
     * @return {@link Integer}
     */
    public Integer update(String id, String key, Object value) {
        return repository.update(id, key, value);
    }

    /**
     * 更新
     *
     * @param id   id
     * @param data 数据
     * @return {@link Integer}
     */
    public Integer update(String id, Map<String, Object> data) {
        return repository.update(id, data);
    }

    /**
     * 更新
     *
     * @param ids  id
     * @param data 数据
     * @return {@link Integer}
     */
    public Integer update(List<? extends Serializable> ids, Map<String, Object> data) {
        return repository.update(ids, data);
    }

    /**
     * 更新
     *
     * @param ids   id
     * @param key   关键词
     * @param value 值
     * @return {@link Integer}
     */
    public Integer update(List<? extends Serializable> ids, String key, Object value) {
        return repository.update(ids, key, value);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        repository = applicationContext.getBean(getDaoClass());
    }

    /**
     * 获取泛型类的类型
     *
     * @return {@link Class}
     */
    public Class<D> getDaoClass() {
        return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
