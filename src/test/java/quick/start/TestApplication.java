package quick.start;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import quick.start.config.MyConfig;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ComponentScan("quick.start")
@ContextConfiguration(classes = MyConfig.class)
public class TestApplication {

     @Autowired
     ApplicationContext applicationContext;

     Logger logger = LoggerFactory.getLogger(TestApplication.class);

     @Test
     public void test() {
          for (String name : applicationContext.getBeanDefinitionNames()) {
               logger.info(name);
          }
     }

}
