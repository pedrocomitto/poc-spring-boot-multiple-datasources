package com.pedrocomitto.poc.multipledatasources.config;

import com.pedrocomitto.poc.multipledatasources.first.domain.entity.FirstDatabaseEntity;
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

import javax.sql.DataSource;

import static java.util.Collections.singletonMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "firstDatabaseEntityManagerFactory",
        transactionManagerRef = "firstDatabaseEntityTransactionManager",
        basePackages = "com.pedrocomitto.multipledatasources.first.repository")
public class FirstDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("first.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource firstDataSource() {
        return firstDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean firstDatabaseEntityManagerFactory(final EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(firstDataSource())
                .properties(singletonMap("hibernate.hbm2ddl.auto", "create-drop"))
                .packages(FirstDatabaseEntity.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager firstDatabaseEntityTransactionManager(final LocalContainerEntityManagerFactoryBean firstDatabaseEntityManagerFactory) {
        return new JpaTransactionManager(firstDatabaseEntityManagerFactory.getObject());
    }

}
