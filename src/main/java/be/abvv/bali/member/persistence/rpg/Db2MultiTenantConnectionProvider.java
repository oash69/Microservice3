package be.abvv.bali.member.persistence.rpg;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class provides a multi tenant enabled connection to the database.
 */
@Component
public class Db2MultiTenantConnectionProvider implements MultiTenantConnectionProvider {
  private static final Logger LOG = LoggerFactory.getLogger(Db2MultiTenantConnectionProvider.class);

  @Autowired
  private DataSource dataSource;

  public Db2MultiTenantConnectionProvider(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Connection getAnyConnection() throws SQLException {
    if (this.dataSource != null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("DB2MultiTenantConnectionProvider::getAnyConnection - {}");
      }
    }

    return this.dataSource.getConnection();
  }

  @Override
  public Connection getConnection(String tenantIdentifier) throws SQLException {
    final Connection connection = this.getAnyConnection();
    try {
      if (LOG.isDebugEnabled()) {
        LOG.debug("DB2MultiTenantConnectionProvider::getConnection - "
                  + "SET SCHEMA {} - isReadOnly: {}",
                  tenantIdentifier,
                  connection.isReadOnly());
      }

      // Use the Thread safe value previously set as set before
      String tenantId = (null != Db2MultiTenantResolver.getTenant()) ? Db2MultiTenantResolver.getTenant() : tenantIdentifier ;
      connection.setSchema(tenantId);
      connection.createStatement().execute("SET SCHEMA " + tenantId);
    } catch (SQLException e) {
      throw new HibernateException("DB2MultiTenantConnectionProvider::getConnection - "
                                   + "Could not alter JDBC connection to specified schema ["
                                   + tenantIdentifier + "]", e);
    }

    return connection;
  }

  @Override
  public void releaseAnyConnection(Connection connection) throws SQLException {
    if (connection != null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("DB2MultiTenantConnectionProvider::releaseAnyConnection - {}",
                  connection.getMetaData().getDriverName());
      }

      if (!connection.isClosed()) {
        connection.close();
      }
    }
  }

  @Override
  public void releaseConnection(String tenantIdentifier, Connection connection)
      throws SQLException {
    try {
      if (LOG.isDebugEnabled()) {
        LOG.debug("DB2MultiTenantConnectionProvider::releaseConnection - "
                  + "SET SCHEMA {}", tenantIdentifier);
      }

      connection.setSchema(tenantIdentifier);
    } catch (SQLException e) {
      throw new HibernateException("DB2MultiTenantConnectionProvider::releaseConnection - "
                                   + "Could not alter JDBC connection to specified schema ["
                                   + tenantIdentifier + "]", e);
    }

    if (!connection.isClosed()) {
      connection.close();
    }
  }

  @Override
  public boolean supportsAggressiveRelease() {
    return false;
  }

  @Override
  public boolean isUnwrappableAs(Class unwrapType) {
    return false;
  }

  @Override
  public <T> T unwrap(Class<T> unwrapType) {
    return null;
  }
}
