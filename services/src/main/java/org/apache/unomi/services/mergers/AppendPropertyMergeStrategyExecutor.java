/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.unomi.services.mergers;

import org.apache.unomi.api.Profile;
import org.apache.unomi.api.PropertyMergeStrategyExecutor;
import org.apache.unomi.api.PropertyType;

import java.util.ArrayList;
import java.util.List;

public class AppendPropertyMergeStrategyExecutor implements PropertyMergeStrategyExecutor {
    public boolean mergeProperty(String propertyName, PropertyType propertyType, List<Profile> profilesToMerge, Profile targetProfile) {
        List result = new ArrayList<>();
        boolean changed = false;

        Object currentPropertyValue = targetProfile.getProperty(propertyName);
        appendToList(result, currentPropertyValue);

        for (Profile profileToMerge : profilesToMerge) {
            changed = appendToList(result, profileToMerge.getProperty(propertyName));
        }

        targetProfile.setProperty(propertyName, result);
        return changed;
    }

    // Return true if "values" was changed
    private boolean appendToList(List values, Object valueToAppend) {
        int currentSize = values.size();

        if (valueToAppend instanceof List) {
            values.addAll((List) valueToAppend);
        } else {
            if (valueToAppend != null) {
                values.add(valueToAppend);
            }
        }

        return values.size() > currentSize;
    }
}
