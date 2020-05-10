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
import com.lamontd.lahmans.writer.neo4j.model.Franchise;
import com.lamontd.lahmans.writer.neo4j.model.League;
import com.lamontd.lahmans.writer.neo4j.model.Team;
import com.lamontd.lahmans.writer.neo4j.model.WorldSeries;
import com.lamontd.lahmans.writer.neo4j.repositories.DivisionRepository;
import com.lamontd.lahmans.writer.neo4j.repositories.FranchiseRepository;
import com.lamontd.lahmans.writer.neo4j.repositories.LeagueRepository;
import com.lamontd.lahmans.writer.neo4j.repositories.TeamRepository;
import com.lamontd.lahmans.writer.neo4j.repositories.WorldSeriesRepository;
import com.lamontd.utils.transport.MappedTransportObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lamontdozierjr
 */
@Service
public class TeamConverter extends MappedTransportObjectConverter<Team> {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private WorldSeriesRepository worldSeriesRepository;
    @Autowired
    private FranchiseRepository franchiseRepository;

    public TeamConverter() {
        super("com.lamontd.lahmans.reader.model.Team");
    }

    @Override
    protected Team convert(MappedTransportObject transportObject) {
        Team team = new Team();
        Map<String, Object> attributes = transportObject.getAttributes();
        team.setId((Integer) attributes.get("ID"));
        team.setYear((Integer) attributes.get("yearID"));
        team.setGames((Integer) attributes.get("gamesPlayed"));
        team.setWins((Integer) attributes.get("wins"));
        team.setLosses((Integer) attributes.get("losses"));
        team.setTeamRank((Integer) attributes.get("teamRank"));

        team.setFranchise(findFranchise((String) attributes.get("franchise")));

        Division teamDivision = findDivision((Integer) attributes.get("uniqueDivisionID"));
        team.setDivisionPlayedIn(teamDivision);
        if (convertToBoolean(attributes.get("divisionWinner"))) {
            team.setDivisionWon(teamDivision);
        }

        League teamLeague = findLeague((String) attributes.get("league"));
        team.setLeagueParticipant(teamLeague);
        if (convertToBoolean(attributes.get("leagueChampion"))) {
            team.setLeagueWon(teamLeague);
            team.setWorldSeriesPlayedIn(findWorldSeries(team.getYear()));
        }

        if (convertToBoolean(attributes.get("worldSeriesChampion"))) {
            team.setWorldSeriesWon(findWorldSeries(team.getYear()));
        }
        return team;
    }

    @Override
    protected void store(Team object) {
        teamRepository.save(object);
    }

    private Division findDivision(Integer divisionID) {
        return divisionRepository.findById(divisionID).orElse(new Division(divisionID));
    }

    private League findLeague(String leagueId) {
        return leagueRepository.findById(leagueId).orElse(new League(leagueId));
    }

    private WorldSeries findWorldSeries(Integer year) {
        return worldSeriesRepository.findById(year).orElse(new WorldSeries(year));
    }

    private Franchise findFranchise(String franchiseId) {
        return franchiseRepository.findById(franchiseId).orElse(new Franchise(franchiseId));
    }

}
