package quick.start.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import quick.start.entity.Entity;
import quick.start.repositorys.condition.Conditions;
import quick.start.repositorys.jdbc.JdbcRepository;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * jdbc默认的抽象类
 *
 * @date 2022/1/4 10:17 下午
 * @Version 1.0.0
 */
public abstract class AbstractDefaultJdbcService<E extends Entity, D extends JdbcRepository<E>> implements ApplicationContextAware {

    private D repository;

    private ApplicationContext applicationContext;

    public Integer add(E entity) {
        return repository.insert(entity);
    }

    public Integer add(List<E> entitys) {
        return repository.insert(entitys);
    }

    public E findById(Serializable id) {
        return repository.findById(id);
    }

    public List<E> find(Conditions conditions) {
        return repository.find(conditions);
    }

    public List<E> findByColumn(String column, Serializable value) {
        return repository.findByColumn(column, value);
    }

    public List<E> findByColumn(String column, Collection<? extends Serializable> values) {
        return repository.findByColumn(column, values);
    }

    public Integer update(E entity) {
        return repository.update(entity);
    }

    public Integer update(String id, String key, Object value) {
        return repository.update(id, key, value);
    }

    public Integer update(String id, Map<String, Object> data) {
        return repository.update(id, data);
    }

    public Integer update(List<? extends Serializable> ids, Map<String, Object> data) {
        return repository.update(ids, data);
    }

    public Integer update(List<? extends Serializable> ids, String key, Object value) {
        return repository.update(ids, key, value);
    }

    @PostConstruct
    void initDao() {
        repository = applicationContext.getBean(getDaoClass());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取泛型类的类型
     *
     * @return
     */
    public Class<D> getDaoClass() {
        return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
