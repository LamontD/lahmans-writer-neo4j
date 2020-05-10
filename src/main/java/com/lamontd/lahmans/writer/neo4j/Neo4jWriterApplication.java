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
package com.lamontd.lahmans.writer.neo4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamontd.lahmans.writer.neo4j.model.services.TransportObjectHandler;
import com.lamontd.lahmans.writer.neo4j.repositories.PlayerRepository;
import com.lamontd.utils.jackson.JacksonMapper;
import com.lamontd.utils.transport.MappedTransportObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class Neo4jWriterApplication {
    private static final Log logger = LogFactory.getLog(Neo4jWriterApplication.class);
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TransportObjectHandler transportObjectHandler;
    
    public static void main(String[] args) {
        SpringApplication.run(Neo4jWriterApplication.class, args);
    }
    
    private void handleMessage(String key, String message) {        
        try {
            final ObjectMapper jacksonMapper = JacksonMapper.getStandardMapper();
            MappedTransportObject incomingObject = jacksonMapper.readValue(message, MappedTransportObject.class);
            
            if (StringUtils.isEmpty(incomingObject.getObjectType())) {
                logger.warn("Received unexpected message that is not a TransportObject: " + message);
            } else if (incomingObject.getAttributes() == null || incomingObject.getAttributes().isEmpty()) {
                logger.warn("Found message of type " + incomingObject.getObjectType() + " but no attributes");
            } else {
                transportObjectHandler.process(incomingObject);
            }
        } catch (JsonProcessingException ex) {
            logger.warn("Error processing incoming message", ex);
        }
    }
    
    @Bean
    public java.util.function.Consumer<KStream<String, String>> process() {
        return input ->
                input.foreach((key, value) -> {
                   handleMessage(key, value);
                });
    }

}
