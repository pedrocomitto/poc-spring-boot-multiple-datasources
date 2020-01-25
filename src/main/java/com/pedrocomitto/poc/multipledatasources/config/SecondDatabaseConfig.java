package com.pedrocomitto.poc.multipledatasources.config;

import com.pedrocomitto.poc.multipledatasources.second.domain.entity.SecondDatabaseEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "secondDatabaseEntityManagerFactory",
        transactionManagerRef = "secondDatabaseEntityTransactionManager",
        basePackages = "com.pedrocomitto.multipledatasources.second.repository")
public class SecondDatabaseConfig {

    @Bean
    @ConfigurationProperties("second.datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource secondDataSource() {
        return secondDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondDatabaseEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondDataSource())
                .properties(singletonMap("hibernate.hbm2ddl.auto", "create-drop"))
                .packages(SecondDatabaseEntity.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager secondDatabaseEntityTransactionManager(final LocalContainerEntityManagerFactoryBean secondDatabaseEntityManagerFactory) {
        return new JpaTransactionManager(secondDatabaseEntityManagerFactory.getObject());
    }

}
