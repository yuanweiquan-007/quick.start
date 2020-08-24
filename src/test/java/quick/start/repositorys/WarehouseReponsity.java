package quick.start.repositorys;

import org.springframework.stereotype.Repository;
import quick.start.entity.Warehouse;
import quick.start.repositorys.jdbc.JdbcRepository;

/**
 * @author yuanweiquan
 */
@Repository
public class WarehouseReponsity extends JdbcRepository<Warehouse> {

}
