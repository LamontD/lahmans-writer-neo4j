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
import com.lamontd.lahmans.writer.neo4j.model.Appearance;
import com.lamontd.lahmans.writer.neo4j.model.Player;
import com.lamontd.lahmans.writer.neo4j.model.Team;
import com.lamontd.lahmans.writer.neo4j.repositories.AppearanceRepository;
import com.lamontd.lahmans.writer.neo4j.repositories.PlayerRepository;
import com.lamontd.lahmans.writer.neo4j.repositories.TeamRepository;
import com.lamontd.utils.transport.MappedTransportObject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lamontdozierjr
 */
@Service
public class AppearanceConverter extends MappedTransportObjectConverter<Appearance> {

    @Autowired
    private AppearanceRepository appearanceRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public AppearanceConverter() {
        super("com.lamontd.lahmans.reader.model.PlayerAppearance");
    }

    @Override
    protected Appearance convert(MappedTransportObject transportObject) {
        Map<String, Object> attributes = transportObject.getAttributes();
        Appearance appearance = new Appearance();
        appearance.setId((Integer) attributes.get("ID"));
        appearance.setGamesPlayed((Integer) attributes.get("totalGamesPlayed"));
        appearance.setGames_P((Integer) attributes.get("gamesAsPitcher"));
        appearance.setGames_C((Integer) attributes.get("gamesAsCatcher"));
        appearance.setGames_1B((Integer) attributes.get("gamesAsFirstBaseman"));
        appearance.setGames_2B((Integer) attributes.get("gamesAsSecondBasemnan"));
        appearance.setGames_3B((Integer) attributes.get("gamesAsThirdBaseman"));
        appearance.setGames_SS((Integer) attributes.get("gamesAsShortStop"));
        appearance.setGames_OF((Integer) attributes.get("gamesAsOutfielder"));
        appearance.setGames_LF((Integer) attributes.get("gamesAsLeftFielder"));
        appearance.setGames_CF((Integer) attributes.get("gamesAsCenterFielder"));
        appearance.setGames_RF((Integer) attributes.get("gamesAsRightFielder"));
        appearance.setGames_DH((Integer) attributes.get("gamesAsDesignatedHitter"));
        appearance.setGames_PH((Integer) attributes.get("gamesAsPinchHitter"));
        appearance.setGames_PR((Integer) attributes.get("gamesAsPinchRunner"));
        appearance.setTeam(findTeam((Integer) attributes.get("teamUniqueID")));
        appearance.setPlayer(findPlayer((String) attributes.get("playerID")));

        return appearance;
    }

    @Override
    protected void store(Appearance object) {
        appearanceRepository.save(object);
    }

    private Team findTeam(Integer teamID) {
        return teamRepository.findById(teamID).orElse(new Team(teamID));
    }

    private Player findPlayer(String playerID) {
        return playerRepository.findById(playerID).orElse(new Player(playerID));
    }
}
