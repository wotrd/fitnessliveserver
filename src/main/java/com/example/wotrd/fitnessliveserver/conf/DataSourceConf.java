package com.example.wotrd.fitnessliveserver.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

/**
 * Created by wkj_pc on 2017/6/5.
 */
@Configuration
public class DataSourceConf {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(8000);
        dataSource.setValidationQuery("SELECT 1");//检测是否为有效的sql
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
        dataSource.setTestWhileIdle(true);//建议配置true，不影响性能，并且保证安全性
        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
        System.out.println("--------------datasource created---------------");
        return dataSource;
    }

}
