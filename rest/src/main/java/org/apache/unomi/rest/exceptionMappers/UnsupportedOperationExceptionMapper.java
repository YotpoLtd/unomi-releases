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
package org.apache.unomi.rest.exceptionMappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.HashMap;

public class UnsupportedOperationExceptionMapper implements ExceptionMapper<UnsupportedOperationException> {

    private static final Logger logger = LoggerFactory.getLogger(UnsupportedOperationExceptionMapper.class.getName());

    @Override
    public Response toResponse(UnsupportedOperationException exception) {
        logger.warn("Unsupported operation", exception);
        HashMap<String, Object> body = new HashMap<>();
        body.put("errorMessage", exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).header("Content-Type", MediaType.APPLICATION_JSON).entity(body).build();
    }
}
