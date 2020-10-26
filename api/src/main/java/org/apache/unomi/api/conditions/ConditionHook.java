package org.apache.unomi.api.conditions;

import org.apache.unomi.api.conditions.Condition;

import java.util.Map;

public interface ConditionHook {

    void executeHook(Condition condition, Map<String, Object> context);
}
