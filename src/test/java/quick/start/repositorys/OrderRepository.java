package quick.start.repositorys;

import quick.start.entity.Order;
import org.springframework.stereotype.Repository;
import quick.start.repositorys.jdbc.JdbcRepository;

@Repository
public class OrderRepository extends JdbcRepository<Order> {

}
