/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lamontd.lahmans.writer.neo4j.model.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamontd.utils.jackson.JacksonMapper;
import com.lamontd.utils.transport.MappedTransportObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author lamontdozierjr
 */
@Service
public class KafkaMessageReader {
    private static final Log logger = LogFactory.getLog(KafkaMessageReader.class);
    
    @Autowired
    private TransportObjectHandler transportObjectHandler;
    
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
        } catch (UnsupportedOperationException unsup) {
            logger.error("Could not process incoming message -> " + message, unsup);
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
