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
import com.lamontd.lahmans.writer.neo4j.model.League;
import com.lamontd.lahmans.writer.neo4j.repositories.LeagueRepository;
import com.lamontd.utils.transport.MappedTransportObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lamontdozierjr
 */
@Service
public class LeagueConverter extends MappedTransportObjectConverter<League> {
    @Autowired
    private LeagueRepository leagueRepository;
    
    public LeagueConverter() {
        super("com.lamontd.lahmans.reader.model.League");
    }

    @Override
    protected League convert(MappedTransportObject transportObject) {
        League league = new League();
        Map<String, Object> attributes = transportObject.getAttributes();
        league.setId((String)attributes.get("id"));
        league.setName((String)attributes.get("league"));
        league.setActive((Boolean)attributes.get("active"));
        return league;
    }    

    @Override
    protected void store(League object) {
        leagueRepository.save(object);
    }

    
}
