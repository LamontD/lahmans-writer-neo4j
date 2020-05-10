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
package com.lamontd.lahmans.writer.neo4j.converters;

import com.lamontd.utils.transport.MappedTransportObjectConverter;
import com.lamontd.lahmans.writer.neo4j.model.Player;
import com.lamontd.lahmans.writer.neo4j.repositories.PlayerRepository;
import com.lamontd.utils.transport.MappedTransportObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lamontdozierjr
 */
@Service
public class PlayerConverter extends MappedTransportObjectConverter<Player> {
    @Autowired
    private PlayerRepository playerRepository;
    
    public PlayerConverter() {
        super("com.lamontd.lahmans.reader.model.Person");
    }

    @Override
    protected Player convert(MappedTransportObject transportObject) {
        Player player = new Player();
        Map<String, Object> attributes = transportObject.getAttributes();
        player.setPlayerId((String)attributes.get("playerID"));
        player.setFirstName((String)attributes.get("nameFirst"));
        player.setLastName((String)attributes.get("nameLast"));
        player.setBirthDate(convertToLocalDate(attributes.get("birthDate")));
        player.setDebut(convertToLocalDate(attributes.get("debutDate")));
        player.setBattingSide((String)attributes.get("battingSide"));
        player.setThrowingSide((String)attributes.get("throwingHand"));
        player.setLastGame(convertToLocalDate(attributes.get("finalGameDate")));
        player.setDeathDate(convertToLocalDate(attributes.get("deathDate")));
        
        return player;
    }

    @Override
    protected void store(Player object) {
        playerRepository.save(object);
    }
    
    
}
