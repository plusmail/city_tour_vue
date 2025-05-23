package com.techelevator.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

@Configuration
public class TestingDatabaseConfig {
    // To use an existing PostgreSQL database, set the following environment variables.
    // Otherwise, a temporary database will be created on the local machine.
    private static final String DB_HOST =
            Objects.requireNonNullElse(System.getenv("DB_HOST"), "localhost");
    private static final String DB_PORT =
            Objects.requireNonNullElse(System.getenv("DB_PORT"), "3306");
    private static final String DB_NAME =
            Objects.requireNonNullElse(System.getenv("DB_NAME"), "final_capstone_test");
    private static final String DB_USERNAME =
            Objects.requireNonNullElse(System.getenv("DB_USERNAME"), "root");
    private static final String DB_PASSWORD =
            Objects.requireNonNullElse(System.getenv("DB_PASSWORD"), "1333");


    private SingleConnectionDataSource adminDataSource;
    private JdbcTemplate adminJdbcTemplate;

    @PostConstruct
    public void setup() {
        if (System.getenv("DB_HOST") == null) {
            adminDataSource = new SingleConnectionDataSource();
            adminDataSource.setUrl("jdbc:mysql://localhost:3306/final_capstone");
            adminDataSource.setUsername("root");
            adminDataSource.setPassword("1333");
            adminJdbcTemplate = new JdbcTemplate(adminDataSource);
            adminJdbcTemplate.update("DROP DATABASE IF EXISTS `" + DB_NAME + "`;");
            adminJdbcTemplate.update("CREATE DATABASE `" + DB_NAME + "`;");

        }
    }

    private DataSource ds = null;
    @Bean
    public DataSource dataSource() throws SQLException {
        if(ds != null) return ds;

        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setUrl(String.format(
                "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false",
                DB_HOST, DB_PORT, DB_NAME
        ));
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setAutoCommit(false); // Rollback after each test

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("/databaseMysql/schema.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("test-data.sql"));

        ds = dataSource;
        return ds;
    }


    @PreDestroy
    public void cleanup() throws SQLException {
        if (adminDataSource != null) {
            adminJdbcTemplate.update("CREATE DATABASE `" + DB_NAME + "`;");
            adminDataSource.getConnection().close();
            adminDataSource.destroy();
        }
    }
}
