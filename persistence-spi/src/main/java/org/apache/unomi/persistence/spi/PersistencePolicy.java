package org.apache.unomi.persistence.spi;


import java.util.HashMap;
import java.util.Map;

public enum PersistencePolicy
{
    NONE("none"),
    BLOCK_UNTIL_READY("block_until_ready"),
    FORCE_READINESS("force_readiness");

    private String policy;

    PersistencePolicy(String policy) {
        this.policy = policy;
    }

    public String getPolicy() {
        return policy;
    }

    private static final Map<String, PersistencePolicy> stringToEnum = new HashMap<>();

    static {
        for(PersistencePolicy policy : PersistencePolicy.values()) {
            stringToEnum.put(policy.getPolicy(), policy);
        }
    }

    public static PersistencePolicy get(String persistencePolicy) {
        return stringToEnum.get(persistencePolicy);
    }
}