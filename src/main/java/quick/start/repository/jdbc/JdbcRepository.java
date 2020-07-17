package quick.start.repository.jdbc;

import quick.start.entity.Entity;
import quick.start.parser.CommandParser;
import quick.start.parser.jdbcparser.JdbcCommandParser;
import quick.start.repository.DefaultAbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JdbcRepository<E extends Entity> extends DefaultAbstractRepository<E> {

     @Autowired
     private JdbcTemplate jdbcTemplate;

     @Override
     public E findById(Serializable id) {
          return null;
     }

     @Override
     public List<E> findByIds(Collection<? extends Serializable> ids) {
          return null;
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
