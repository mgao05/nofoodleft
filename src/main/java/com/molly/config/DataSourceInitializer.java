package com.molly.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


//dependency process using configuration
@Configuration
public class DataSourceInitializer {
    public String databaseUrl = "jdbc:postgresql://localhost:5432/noFoodLeft";
    public String databaseUserName = "molly";
    public String databasePassword = "password";
    public String driverClassName = "org.postgresql.ds.PGSimpleDataSource";

    @Bean(name="dataSource")
    public DataSource getDataSource (){
        DataSource dataSource=createDataSource();
        return dataSource;
    }


    private BasicDataSource createDataSource() { //if want to check class property, press command and click
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUserName);
        dataSource.setPassword(databasePassword);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }

}