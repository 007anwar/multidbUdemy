package com.example.multi.db.config;


import com.example.multi.db.product.entities.Product;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "productEntityManagerFactory",transactionManagerRef = "productTransactionManager",basePackages = {"com.example.multi.db.product"})
@EnableTransactionManagement
public class ProductDataSourceConfig {


    @ConfigurationProperties("spring.datasource-product")
    @Bean("productDataSourceProperties")
    public DataSourceProperties dataSourceProperties()
    {
        return new DataSourceProperties();
    }

    @Bean(name = "productDataSource")
    public DataSource dataSource()
    {
        return dataSourceProperties().
                initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }


    @Bean("productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactory(EntityManagerFactoryBuilder factoryBuilder)
    {
           return  factoryBuilder.dataSource(dataSource()).packages(Product.class).persistenceUnit("productds").build();
    }

    @Primary
    @Bean("productTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("productEntityManagerFactory") EntityManagerFactory entityManagerFactory)
    {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
