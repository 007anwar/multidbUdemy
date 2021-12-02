package com.example.multi.db.config;


import com.example.multi.db.coupoun.entities.Coupon;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "couponEntityManagerFactory",transactionManagerRef = "couponTransactionManager",basePackages = {"com.example.multi.db.coupoun"})
@EnableTransactionManagement
public class CouponDataSourceConfig {


    @ConfigurationProperties("spring.datasource-coupon")
    @Bean("couponDataSourceProperties")
    @Primary
    public DataSourceProperties dataSourceProperties()
    {
        return new DataSourceProperties();
    }

    @Bean(name = "couponDataSource")
    @Primary
    public DataSource dataSource()
    {
        return dataSourceProperties().
                initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }


    @Primary
    @Bean("couponEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactory(EntityManagerFactoryBuilder factoryBuilder)
    {
           return  factoryBuilder.dataSource(dataSource()).packages(Coupon.class).persistenceUnit("couponds").build();
    }

    @Primary
    @Bean("couponTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("couponEntityManagerFactory") EntityManagerFactory entityManagerFactory)
    {
        return new JpaTransactionManager(entityManagerFactory);
    }
    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}
