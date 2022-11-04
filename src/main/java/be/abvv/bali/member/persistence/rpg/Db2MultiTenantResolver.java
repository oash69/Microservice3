package be.abvv.bali.member.persistence.rpg;

import be.abvv.bali.member.exception.InvalidInputException;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class allows us to switch from one tenant to the other.
 */
@Component
public class Db2MultiTenantResolver implements CurrentTenantIdentifierResolver {
  private static final Logger LOG = LoggerFactory.getLogger(Db2MultiTenantResolver.class);

  public static final String DEFAULT_SCHEMA_PREFIX = "ABVVPFX";
  public static final String DEFAULT_TENANT_IDENTIFIER = "R";

  static {
    Map<String, String> tenantMap = new HashMap<>();
    tenantMap.put("0", "Common + Merge");
    tenantMap.put("A", "Antwerpen");
    tenantMap.put("C", "Charleroi");
    tenantMap.put("G", "Oost-Vlaanderen");
    tenantMap.put("L", "Vlaams Brabant");
    tenantMap.put("R", "Luxemburg");
    tenantMap.put("V", "Verviers");
    tenantMap.put("W", "Namur");
    tenantMap.put("Y", "Tournai / Wallonie-Picarde");
    tenantMap.put("1", "Brussels");
    tenantMap.put("2", "Limburg");
    tenantMap.put("3", "Mons");
    tenantMap.put("5", "West-Vlaanderen");
    tenantMap.put("6", "Brabant-Wallon / Centre");
    tenantMap.put("7", "Liege");

    validTenants = Collections.unmodifiableMap(tenantMap);
  }

  // The variable currentTenant HAS TO BE thread safe
  //private static final ThreadLocal<String> currentTenant = new ThreadLocal<String>();
  private static final Map<String, String> validTenants;

  public static void reset(String tenantIdentifier) {
    Db2TenantContext.clear();
  }

  public static Map<String, String> getTenants() {
    return validTenants;
  }

  /**
   * Sets the database tenant.
   *
   * @param tenantIdentifier The new database tenant
   */
  public static void setTenant(String tenantIdentifier) {
    String lastTenantChar = tenantIdentifier.substring(tenantIdentifier.length() - 1);

    if (Db2MultiTenantResolver.validTenants.containsKey(lastTenantChar)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("DB2MultiTenantResolver::setTenant - {}", tenantIdentifier);
      }
      Db2TenantContext.setCurrentTenant(tenantIdentifier);
      /*currentTenant.set(tenantIdentifier);*/
    } else {
      if (LOG.isDebugEnabled()) {
        LOG.debug("DB2MultiTenantResolver::setTenant - INVALID TENANT: {}", tenantIdentifier);
      }
    }
  }

  public static String getTenant() {
    return Db2TenantContext.getCurrentTenant();
/*
    return currentTenant.get();
*/
  }

  /**
   * Utility method that parses a comma separated list of tenants.
   * It verifies that all tenants in the list are valid.
   *
   * @param regionList comma separated list of tenants
   * @return A comma separated list of valid tenants
   * @throws InvalidInputException Invalid Input Exception
   */
  public static List<String> normalizeRegionList(List<String> regionList)
      throws InvalidInputException {
    List<String> result = new ArrayList<>();

    for (String region : regionList) {
      if ((region.length() != 1)
          || (!Db2MultiTenantResolver.getTenants().containsKey(region))) {
        throw new InvalidInputException(
            String.format("Invalid region parameter value. '%s' is "
                        + "not part of the possible values %s",
                region,
                Db2MultiTenantResolver.getTenants().keySet().stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(", ", "{ ", " }"))),
            InvalidInputException.INVALID_REGION);
      }

      result.add(region);
    }

    return result;
  }

  @Override
  public String resolveCurrentTenantIdentifier() {
    String returnString = Db2MultiTenantResolver.DEFAULT_SCHEMA_PREFIX
                          + Db2MultiTenantResolver.DEFAULT_TENANT_IDENTIFIER;

    String currentTenantId = Db2MultiTenantResolver.getTenant();
    if (currentTenantId != null) {
      returnString = currentTenantId;
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("TenantIdentifierResolver::resolveCurrentTenantIdentifier - "
                + "currentTenantId: {} - returnString: {}", currentTenantId, returnString);
    }

    return returnString;
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}
