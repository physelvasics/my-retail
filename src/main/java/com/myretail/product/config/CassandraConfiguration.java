package com.myretail.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;

/**
 * This configuration class produces CassandraClusterFactoryBean.
 *
 * @author Selvaraj Karuppusamy
 */
@Configuration
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${cassandra.keyspace}")
    private String keySpace;

    @Value("${cassandra.contact.point}")
    private String contactPoint;

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster =
                new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoint);
        cluster.setPort(9042);
        return cluster;
    }
}
