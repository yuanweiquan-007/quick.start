package quick.start;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import quick.start.config.MyConfig;
import quick.start.entity.Order;
import quick.start.repositorys.OrderRepository;
import quick.start.repositorys.condition.Conditions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ComponentScan("quick.start")
@ContextConfiguration(classes = MyConfig.class)
public class TestReponsitory {

     @Autowired
     OrderRepository orderRepository;

     Logger logger = LoggerFactory.getLogger(TestReponsitory.class);

     @Test
     public void find() {
          orderRepository.find().forEach(order -> logger.info(order.toString()));
     }

     @Test
     public void findById() {
          logger.info("{}", orderRepository.findById("1593238076676"));
     }

     @Test
     public void findByIds() {
          orderRepository.findByIds(Arrays.asList("1593238076676", "1593238089359")).forEach(x -> {
               logger.info("{}", x.toString());
          });
     }

     @Test
     public void findByConditions() {
          Conditions conditions = new Conditions()
                  .equal("orderCode", "1593238616437")
                  .lessThenOrEqual("orderId", 13)
                  .greaterThen("orderId", 10)
                  .desc("orderId")
                  .asc("orderCode")
                  .limit(10);
          orderRepository.find(conditions).forEach(x -> {
               logger.info("{}", x.toString());
          });
     }

     @Test
     public void findByPage() {
          Conditions conditions = new Conditions()
                  .equal("orderCode", "1593238616437")
                  .lessThenOrEqual("orderId", 13)
                  .greaterThen("orderId", 10);
          orderRepository.findByPage(conditions, 5, 1).getResults().forEach(x -> {
               logger.info("{}", x.toString());
          });
     }

     @Test
     public void has() {
          logger.info("{}", orderRepository.has("15932380766761"));
     }

     @Test
     public void findByColumn() {
          orderRepository.findByColumn("orderId", 9).forEach(x -> {
               logger.info("{}", x.toString());
          });
          orderRepository.findByColumn("orderId", Arrays.asList(9, 10)).forEach(x -> {
               logger.info("{}", x.toString());
          });
     }

     @Test
     public void insert() {
          Order order = createOrder();
          logger.info("{}", orderRepository.insert(order));
     }

     private Order createOrder() {
          Order order = new Order();
          order.setOrderCode(String.valueOf(System.currentTimeMillis()));
          order.setRemark(LocalDateTime.now().toString());
          return order;
     }

     @Test
     public void batchInsert() {
          List<Order> orders = IntStream.rangeClosed(1, 5)
                  .mapToObj(x -> createOrder())
                  .collect(Collectors.toList());
          logger.info("{}", orderRepository.insert(orders));
     }

     @Test
     public void delete() {
          orderRepository.delete("1593238076676");
          orderRepository.delete(Arrays.asList("1593238076676", "1593238089359"));
     }

}
