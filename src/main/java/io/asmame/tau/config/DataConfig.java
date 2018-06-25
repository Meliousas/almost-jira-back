package io.asmame.tau.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DataConfig {


    @Primary
    @Bean(name="dataSource")
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName("org.postgresql.Driver"));
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%s/%s", "localhost", "5432", "travello"));
        dataSource.setUsername("postgres");
        dataSource.setPassword("grubas");
        return dataSource;
    }


    @Autowired
    @Bean(name="flyway")
    public Flyway getFlywayBean(@Qualifier("dataSource") DataSource dataSource, @Value("${application.db.schema}") String schema) {
        Flyway bean = new Flyway();
        bean.setDataSource(dataSource);
        bean.setSchemas(schema);
        bean.setOutOfOrder(true);
        bean.migrate();

        return bean;
    }

}
