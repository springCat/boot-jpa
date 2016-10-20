package org.springcat.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {
 
    @Bean
    public DataSource druidDataSource(@Value("${spring.datasource.driver-class-name}") String driver,
                                      @Value("${spring.datasource.url}") String url,
                                      @Value("${spring.datasource.username}") String username,
                                      @Value("${spring.datasource.password}") String password,
                                      @Value("${druid.filters}") String filters) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);

        if(isNotEmpty(filters)) {
            try {
                druidDataSource.setFilters(filters);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        druidDataSource.setMaxActive(20);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setMinIdle(1);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("select 'x'");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxOpenPreparedStatements(20);
        druidDataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");

        return druidDataSource;
    }
 
    @Bean
    public FilterRegistrationBean filterRegistrationBean(@Value("${druid.path}") String path) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,"+path);
        return filterRegistrationBean;
    }


    @Bean
    public ServletRegistrationBean servletRegistrationBean(@Value("${druid.path}") String path,
                                                           @Value("${druid.loginUsername}") String loginUsername,
                                                           @Value("${druid.loginPassword}") String loginPassword,
                                                           @Value("${druid.allow}") String allow,
                                                           @Value("${druid.deny}") String deny,
                                                           @Value("${druid.resetEnable}") String resetEnable) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), path);
        servletRegistrationBean.addInitParameter("loginUsername",loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword",loginPassword);
        servletRegistrationBean.addInitParameter("allow",allow);
        servletRegistrationBean.addInitParameter("deny",deny);
        servletRegistrationBean.addInitParameter("resetEnable",resetEnable);

        return servletRegistrationBean;
    }

    private boolean isNotEmpty(String s){
        return s == null || s.length() == 0;
    }

}