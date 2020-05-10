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
import com.lamontd.lahmans.writer.neo4j.model.Award;
import com.lamontd.lahmans.writer.neo4j.model.League;
import com.lamontd.lahmans.writer.neo4j.model.Player;
import com.lamontd.lahmans.writer.neo4j.model.PlayerHonor;
import com.lamontd.lahmans.writer.neo4j.repositories.AwardRepository;
import com.lamontd.lahmans.writer.neo4j.repositories.LeagueRepository;
import com.lamontd.lahmans.writer.neo4j.repositories.PlayerHonorRepository;
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
public class PlayerHonorConverter extends MappedTransportObjectConverter<PlayerHonor> {

    @Autowired
    private PlayerHonorRepository playerHonorRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private AwardRepository awardRepository;
    @Autowired
    private LeagueRepository leagueRepository;

    public PlayerHonorConverter() {
        super("com.lamontd.lahmans.reader.model.PlayerAward");
    }

    @Override
    protected PlayerHonor convert(MappedTransportObject transportObject) {
        Map<String, Object> attributes = transportObject.getAttributes();
        PlayerHonor honor = new PlayerHonor();
        honor.setId((Integer) attributes.get("ID"));
        honor.setYear((Integer) attributes.get("yearID"));
        honor.setTie(convertToBoolean(attributes.get("tie")));
        honor.setPosition((String) attributes.get("notes"));
        honor.setAward(findAward((String) attributes.get("awardID")));
        honor.setLeague(findLeague((String) attributes.get("league")));
        honor.setPlayer(findPlayer((String) attributes.get("playerID")));
        return honor;
    }

    @Override
    protected void store(PlayerHonor object) {
        playerHonorRepository.save(object);
    }

    private Award findAward(String awardId) {
        return awardRepository.findById(awardId).orElse(new Award(awardId));
    }

    private League findLeague(String leagueId) {
        return leagueRepository.findById(leagueId).orElse(new League(leagueId));
    }

    private Player findPlayer(String playerId) {
        return playerRepository.findById(playerId).orElse(new Player(playerId));
    }

}
