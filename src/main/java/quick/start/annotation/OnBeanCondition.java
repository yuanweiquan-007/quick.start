package quick.start.annotation;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author yuanweiquan
 */
public class OnBeanCondition implements Condition {

     @Override
     public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
          ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
          if (metadata.isAnnotated(ConditionalOnBean.class.getName())) {
               return cocnditionalOnBeanMatches(metadata, beanFactory);
          } else {
               return false;
          }
     }

     private boolean cocnditionalOnBeanMatches(AnnotatedTypeMetadata metadata, ConfigurableListableBeanFactory beanFactory) {
          Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnBean.class.getName());
          Class[] types = (Class[]) annotationAttributes.get("value");
          for (Class type : types) {
               if (beanFactory.getBeanNamesForType(type).length <= 0) {
                    return false;
               }
          }
          return true;
     }
}
