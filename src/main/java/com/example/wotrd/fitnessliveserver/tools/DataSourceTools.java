package com.example.wotrd.fitnessliveserver.tools;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * Created by wkj_pc on 2017/8/16.
 */
public class DataSourceTools {
    public static DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fitnesslive.db?useUnicode=true&characterEncoding=utf-8");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(30);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(8000);
        dataSource.setValidationQuery("SELECT 1");//检测是否为有效的sql
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
        dataSource.setTestWhileIdle(true);//建议配置true，不影响性能，并且保证安全性
        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
        return dataSource;
    }
}

