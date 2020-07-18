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
import quick.start.repositorys.OrderRepository;

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

}
