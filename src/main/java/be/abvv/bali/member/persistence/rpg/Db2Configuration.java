package be.abvv.bali.member.persistence.rpg;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * DB2 Database configuration.
 *
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory",
                       transactionManagerRef = "db2TransactionManager",
                       basePackages = {"be.abvv.bali.member.persistence.rpg.dao"})
public class Db2Configuration {
  @Autowired
  private Environment env;

  /**
   * This method loads DB2 specific configuration parameters in order to connect to the database.
   *
   * @return DataSource
   */
  @Primary
  @Bean(name = "db2DataSource")
  public Db2CustomRoutingDataSource dataSource() {
    final String datasourceUsername =
        env.getRequiredProperty("spring.db2.datasource.username");
    final String datasourcePassword =
        env.getRequiredProperty("spring.db2.datasource.password");
    final String datasourceUrl      =
        env.getRequiredProperty("spring.db2.datasource.url");
    final String datasourceDriver   =
        env.getRequiredProperty("spring.db2.datasource.driverClassName");
    final String datasourceSchema   =
        env.getRequiredProperty("spring.db2.datasource.default_schema_name");

    Db2CustomRoutingDataSource customDataSource = new Db2CustomRoutingDataSource();
    Map<Object, Object> datasourceMap = new HashMap();

    for(String key : Db2MultiTenantResolver.getTenants().keySet()) {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(datasourceDriver);
      dataSource.setUrl(datasourceUrl);
      dataSource.setUsername(datasourceUsername);
      dataSource.setPassword(datasourcePassword);
      dataSource.setSchema(datasourceSchema + key);

      try {
        dataSource.getConnection().setAutoCommit(false);
      } catch (Exception throwables) {
        throwables.printStackTrace();
      }

      datasourceMap.put(datasourceSchema + key, dataSource);
    }

    customDataSource.setTargetDataSources(datasourceMap);

    return customDataSource;
  }

  /**
   * DB2 Entity Manager Factory.
   *
   * @param dataSource Data Source
   * @return Local Container Entity Manager Factory Bean
   */
  @Primary
  @Bean(name = "db2EntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(@Qualifier("db2DataSource") Db2CustomRoutingDataSource dataSource,
                                                                        Db2MultiTenantConnectionProvider multiTenantConnectionProviderImpl,
                                                                        Db2MultiTenantResolver currentTenantIdentifierResolverImpl) {
    LocalContainerEntityManagerFactoryBean em
        = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setPackagesToScan(
        new String[] { "be.abvv.bali.member.persistence.rpg.domain" });

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    Properties properties = new Properties();

    properties.put(AvailableSettings.HBM2DDL_AUTO,
        env.getProperty("spring.db2.datasource.hibernate.hbm2ddl.auto",
            String.class,
            "validate"));
    properties.put(AvailableSettings.DIALECT,
        env.getProperty("spring.db2.datasource.hibernate.dialect",
            String.class,
            "org.hibernate.dialect.DB2400Dialect"));

    properties.put(AvailableSettings.DEFAULT_SCHEMA,
            env.getProperty("spring.db2.datasource.default_schema_name",
                    String.class,
                    "ABVVTFX")
                    + Db2MultiTenantResolver.DEFAULT_TENANT_IDENTIFIER);
    properties.put(AvailableSettings.DEFAULT_SCHEMA,
            env.getProperty("spring.jpa.properties.hibernate.default_schema",
                    String.class,
                    "ABVVTFX")
                    + Db2MultiTenantResolver.DEFAULT_TENANT_IDENTIFIER);

    properties.put(AvailableSettings.MULTI_TENANT,
        MultiTenancyStrategy.SCHEMA);
    properties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER,
            multiTenantConnectionProviderImpl);
    properties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER,
            currentTenantIdentifierResolverImpl);

    properties.put(AvailableSettings.SHOW_SQL,
        env.getProperty("spring.jpa.show-sql",
            String.class,
          "true"));
    properties.put(AvailableSettings.FORMAT_SQL,
        env.getProperty("spring.jpa.properties.hibernate.format_sql",
            String.class,
          "true"));
    //properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");

    em.setJpaProperties(properties);

    dataSource.afterPropertiesSet();

    return em;
  }

  @Primary
  @Bean(name = "db2TransactionManager")
  public PlatformTransactionManager db2TransactionManager(
      @Qualifier("db2EntityManagerFactory") EntityManagerFactory db2EntityManagerFactory) {
    return new JpaTransactionManager(db2EntityManagerFactory);
  }
}
