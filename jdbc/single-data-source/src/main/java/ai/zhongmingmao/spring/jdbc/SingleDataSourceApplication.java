package ai.zhongmingmao.spring.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SpringBootApplication
public class SingleDataSourceApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SingleDataSourceApplication.class, args);
  }

  DataSource dataSource;
  JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {
    showConnection();
    showData();
  }

  private void showConnection() throws SQLException {
    log.info(dataSource.toString());
    Connection connection = dataSource.getConnection();
    log.info(connection.toString());
    connection.close();
  }

  private void showData() {
    jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row -> log.info(row.toString()));
  }
}
