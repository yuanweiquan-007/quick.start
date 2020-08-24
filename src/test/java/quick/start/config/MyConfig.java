package quick.start.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("quick.start")
public class MyConfig {

     @Bean
     public JdbcTemplate jdbcTemplate() {
          JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
          return jdbcTemplate;
     }

//     @Bean
//     public DataSource dataSource() {
//          BasicDataSource dataSource = new BasicDataSource();
//          dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//          dataSource.setUsername("root");
//          dataSource.setPassword("root");
//          dataSource.setUrl("jdbc:mysql://localhost:3306/spring_study?zeroDateTimeBehavior=convertToNull");
//          return dataSource;
//     }

     @Bean
     public DataSource dataSource() {
          BasicDataSource dataSource = new BasicDataSource();
          dataSource.setDriverClassName("com.mysql.jdbc.Driver");
          dataSource.setUsername("ywq");
          dataSource.setPassword("bcw@ywq123456");
          dataSource.setUrl("jdbc:mysql://122.224.240.99:10003/ecall_core_archives?zeroDateTimeBehavior=convertToNull");
          return dataSource;
     }

}
