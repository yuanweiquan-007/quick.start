package quick.start;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Beans implements ApplicationContextAware {

     protected static ApplicationContext applicationContext;

     @Override
     public void setApplicationContext(ApplicationContext context) throws BeansException {
          Beans.applicationContext = context;
     }

     public static <T> T get(String name) {
          return (T) applicationContext.getBean(name);
     }

     public static <T> T get(Class<T> requiredType) {
          return applicationContext.getBean(requiredType);
     }

     public static boolean has(String name) {
          return applicationContext.containsBean(name);
     }

}
