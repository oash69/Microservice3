package be.abvv.bali.member.persistence.rpg;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Class that initializes the multi tenancy behaviour.
 *
 */
@Component
public class Db2NamingStrategy extends SpringPhysicalNamingStrategy {
  private static final Logger LOG = LoggerFactory.getLogger(Db2NamingStrategy.class);

  @Resource
  public Environment env;

  @Override
  public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
    Identifier returnedIentifier = null;

    if ((name != null) && (!name.getText().isEmpty()) && (null != env)) {
      String schemaName = env.getProperty("spring.db2.datasource." + name.getText() + ".schema");
      if (!schemaName.isEmpty()) {
        returnedIentifier = new Identifier(schemaName, name.isQuoted());
      }
    } else {
      returnedIentifier = name;
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("++++++++++++++++++++++++++++++++++++");
      LOG.debug("DB2NamingStrategy::toPhysicalSchemaName - name: {}", name);
    }

    return returnedIentifier;
  }

  @Override
  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
    Identifier returnedIentifier = null;

    if ((name != null) && (!name.getText().isEmpty()) && (null != env)) {
      String tableName = env.getProperty("spring.db2.datasource." + name.getText() + ".table");
      if (!tableName.isEmpty()) {
        returnedIentifier = new Identifier(tableName, name.isQuoted());
      }
    } else {
      returnedIentifier = name;
    }

    return returnedIentifier;
  }
}
