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
 * limitations under the License
 */
package org.apache.unomi.itests;

import org.apache.unomi.api.services.EventService;
import org.apache.unomi.api.services.ProfileService;
import org.apache.unomi.api.services.DefinitionsService;
import org.apache.unomi.persistence.spi.PersistenceService;
import org.apache.unomi.api.Event;
import org.apache.unomi.api.Profile;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.After;

import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.util.Filter;

import javax.inject.Inject;
import java.util.Date;
import org.junit.Assert;


/**
 * An integration test for the event service
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class EventServiceIT extends BaseIT {

    private final static String TEST_PROFILE_ID = "test-profile-id";

    @Inject @Filter(timeout = 600000)
    protected EventService eventService;

    @Inject
    @Filter(timeout = 600000)
    protected ProfileService profileService;


    @Inject
    @Filter(timeout = 600000)
    protected PersistenceService persistenceService;

    @Inject
    @Filter(timeout = 600000)
    protected DefinitionsService definitionsService;

    @After
    public void tearDown() {
        TestUtils.removeAllEvents(definitionsService, persistenceService);
        TestUtils.removeAllSessions(definitionsService, persistenceService);
        TestUtils.removeAllProfiles(definitionsService, persistenceService);
        persistenceService.refresh();
    }

    @Test
    public void test_EventExistenceWithProfileId() throws InterruptedException{
        String eventId = "test-event-id2";
        String profileId = "test-profile-id";
        String eventType = "test-type";
        Profile profile = new Profile(profileId);
        Event event = new Event(eventId, eventType, null, profile, null, null, null, new Date());
        profileService.save(profile);
        eventService.send(event);
        Thread.sleep(2000);
        boolean exist = eventService.hasEventAlreadyBeenRaised(event);
        Assert.assertTrue(exist);
    }

}