package ai.zhongmingmao.spring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SpringBean {

  public static void main(String[] args) throws SQLException {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("applicationContext.xml");

    SpringBean bean = applicationContext.getBean(SpringBean.class);
    bean.showDataSource();
  }

  @Bean(destroyMethod = "close")
  public DataSource dataSource() throws SQLException {
    Properties properties = new Properties();
    properties.setProperty("driverClassName", "org.h2.Driver");
    properties.setProperty("url", "jdbc:h2:mem:test");
    properties.setProperty("username", "sa");
    return BasicDataSourceFactory.createDataSource(properties);
  }

  @Bean
  public PlatformTransactionManager transactionManager() throws SQLException {
    return new DataSourceTransactionManager(dataSource());
  }

  @Autowired private DataSource dataSource;

  private void showDataSource() throws SQLException {
    System.out.println(dataSource.toString());
    Connection connection = dataSource.getConnection();
    System.out.println(connection.toString());
    connection.close();
  }
}
