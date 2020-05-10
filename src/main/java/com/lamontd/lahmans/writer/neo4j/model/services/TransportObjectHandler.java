/*
 * Copyright 2020 lamontdozierjr.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
  */
package com.lamontd.lahmans.writer.neo4j.model.services;

import com.lamontd.utils.transport.MappedTransportObjectConverter;
import com.lamontd.utils.transport.MappedTransportObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author lamontdozierjr
 */
@Service
public class TransportObjectHandler {

    private static final Log logger = LogFactory.getLog(TransportObjectHandler.class);
    @Autowired
    private ApplicationContext applicationContext;

    public void process(MappedTransportObject transportObject) {
        for (MappedTransportObjectConverter converter : applicationContext.getBeansOfType(MappedTransportObjectConverter.class).values()) {
            if (converter.canHandle(transportObject)) {
                logger.debug("Using " + converter.getClass() + " to handle transport object of type " + transportObject.getObjectType());
                converter.convertAndStore(transportObject);
                return;
            }
        }
        throw new UnsupportedOperationException("Object type [" + transportObject.getObjectType() + "] not supported yet");
    }
}
