package be.abvv.bali.member.persistence.rpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class Db2CustomRoutingDataSource extends AbstractRoutingDataSource {
  @Autowired
  private Environment env;

  @Override
  protected Object determineCurrentLookupKey() {
    String tenantId =  Db2MultiTenantResolver.getTenant();
    if (null == tenantId) {
      final String datasourceSchema   =
          env.getRequiredProperty("spring.db2.datasource.default_schema_name");

      tenantId = datasourceSchema + Db2MultiTenantResolver.DEFAULT_TENANT_IDENTIFIER;
    } else {
      System.out.println("Tenant not null: " + tenantId);
    }

    return tenantId;
  }
}
