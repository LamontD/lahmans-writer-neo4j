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
package com.lamontd.lahmans.writer.neo4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author lamontdozierjr
 */
@NodeEntity
public class PlayerHonor {

    @Id
    private Integer id;
    private Integer year;
    private String position;
    private Boolean tie;

    @JsonIgnoreProperties("playerHonors")
    @Relationship(type = RelationshipDefs.PLAYER_HONOR_WINNER, direction = Relationship.INCOMING)
    private Player player;

    @JsonIgnoreProperties("playerHonors")
    @Relationship(type = RelationshipDefs.PLAYER_HONOR_LEAGUE)
    private League league;
    
    @JsonIgnoreProperties("playerHonors")
    @Relationship(type = RelationshipDefs.PLAYER_HONOR_AWARD)
    private Award award;

    public PlayerHonor() {
    }

    public PlayerHonor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getTie() {
        return tie;
    }

    public void setTie(Boolean tie) {
        this.tie = tie;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

}
