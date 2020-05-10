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
import com.lamontd.lahmans.writer.neo4j.model.Division;
import com.lamontd.lahmans.writer.neo4j.model.League;
import com.lamontd.lahmans.writer.neo4j.repositories.DivisionRepository;
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
public class DivisionConverter extends MappedTransportObjectConverter<Division> {
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private LeagueRepository leagueRepository;

    public DivisionConverter() {
        super("com.lamontd.lahmans.reader.model.Division");
    }

    @Override
    protected Division convert(MappedTransportObject transportObject) {
        Division division = new Division();
        Map<String, Object> attributes = transportObject.getAttributes();
        division.setId((Integer)attributes.get("ID"));
        division.setDivision((String)attributes.get("division"));
        division.setActive((Boolean)attributes.get("active"));

        String leagueId = (String)attributes.get("leagueID");
        League myLeague = leagueRepository.findById(leagueId).get();
        if (myLeague == null) {
            // For better or worse, let's add something so the relationship is created
            myLeague = new League();
            myLeague.setId(leagueId);
        }
        division.setLeague(myLeague);
        return division;
    }

    @Override
    protected void store(Division object) {
        divisionRepository.save(object);
    }
    
    
    
}
