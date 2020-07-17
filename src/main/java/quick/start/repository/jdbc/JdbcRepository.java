package quick.start.repository.jdbc;

import org.springframework.util.StringUtils;
import quick.start.entity.Entity;
import quick.start.parser.CommandParser;
import quick.start.parser.jdbcparser.JdbcCommandParser;
import quick.start.repository.DefaultAbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import quick.start.repository.command.ExecuteCommandMeta;
import quick.start.repository.command.Select;
import quick.start.util.EntityMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JdbcRepository<E extends Entity> extends DefaultAbstractRepository<E> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected List<E> select(Select<E> select) {
        checkTableName(select);
        ExecuteCommandMeta commandMeta = commandParser().parser(select);
        if (StringUtils.isEmpty(commandMeta.getCommand())) {
            throw new IllegalArgumentException("select语句解析异常");
        }
        try {
            return EntityMapper.toEntityList(this.jdbcTemplate.queryForList(commandMeta.getCommand(), commandMeta.getParames().toArray()), select.getMeta().getEClass());
        } catch (Exception e) {
            logger.info("查询失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<E> findByColumn(String column, Serializable value) {
        return null;
    }

    @Override
    public List<E> findByColumn(String column, Collection<? extends Serializable> values) {
        return null;
    }

    @Override
    public List<E> find() {
        return null;
    }

    @Override
    public boolean has(Serializable id) {
        return false;
    }

    @Override
    public boolean insert(E entity) {
        return false;
    }

    @Override
    public boolean insert(List<E> entitys) {
        return false;
    }

    @Override
    public boolean update(E entity) {
        return false;
    }

    @Override
    public boolean update(List<? extends Serializable> ids, Map<String, Object> data) {
        return false;
    }

    @Override
    public boolean update(String id, Map<String, Object> data) {
        return false;
    }

    @Override
    public boolean update(String id, String key, Object value) {
        return false;
    }

    @Override
    public boolean update(List<? extends Serializable> ids, String key, Object value) {
        return false;
    }

    @Override
    public boolean delete(Serializable id) {
        return false;
    }

    @Override
    public boolean delete(List<? extends Serializable> ids) {
        return false;
    }

    @Override
    public boolean delete(String column, Collection<? extends Serializable> values) {
        return false;
    }

    @Override
    protected CommandParser commandParser() {
        return new JdbcCommandParser();
    }

}
