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
public class Appearance {
    @Id
    private Integer id;
    private Integer year;
    private Integer gamesPlayed;
    private Integer games_P;
    private Integer games_C;
    private Integer games_1B;
    private Integer games_2B;
    private Integer games_3B;
    private Integer games_SS;
    private Integer games_LF;
    private Integer games_RF;
    private Integer games_CF;
    private Integer games_OF;
    private Integer games_DH;
    private Integer games_PH;
    private Integer games_PR;
    
    @JsonIgnoreProperties("appearances")
    @Relationship(type=RelationshipDefs.PLAYER_APPEARANCE, direction=Relationship.INCOMING)
    private Player player;
    
    @JsonIgnoreProperties("appearances")
    @Relationship(type=RelationshipDefs.APPEARANCE_TEAM)
    private Team team;

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

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Integer getGames_P() {
        return games_P;
    }

    public void setGames_P(Integer games_P) {
        this.games_P = games_P;
    }

    public Integer getGames_C() {
        return games_C;
    }

    public void setGames_C(Integer games_C) {
        this.games_C = games_C;
    }

    public Integer getGames_1B() {
        return games_1B;
    }

    public void setGames_1B(Integer games_1B) {
        this.games_1B = games_1B;
    }

    public Integer getGames_2B() {
        return games_2B;
    }

    public void setGames_2B(Integer games_2B) {
        this.games_2B = games_2B;
    }

    public Integer getGames_3B() {
        return games_3B;
    }

    public void setGames_3B(Integer games_3B) {
        this.games_3B = games_3B;
    }

    public Integer getGames_SS() {
        return games_SS;
    }

    public void setGames_SS(Integer games_SS) {
        this.games_SS = games_SS;
    }

    public Integer getGames_LF() {
        return games_LF;
    }

    public void setGames_LF(Integer games_LF) {
        this.games_LF = games_LF;
    }

    public Integer getGames_RF() {
        return games_RF;
    }

    public void setGames_RF(Integer games_RF) {
        this.games_RF = games_RF;
    }

    public Integer getGames_CF() {
        return games_CF;
    }

    public void setGames_CF(Integer games_CF) {
        this.games_CF = games_CF;
    }

    public Integer getGames_OF() {
        return games_OF;
    }

    public void setGames_OF(Integer games_OF) {
        this.games_OF = games_OF;
    }

    public Integer getGames_DH() {
        return games_DH;
    }

    public void setGames_DH(Integer games_DH) {
        this.games_DH = games_DH;
    }

    public Integer getGames_PH() {
        return games_PH;
    }

    public void setGames_PH(Integer games_PH) {
        this.games_PH = games_PH;
    }

    public Integer getGames_PR() {
        return games_PR;
    }

    public void setGames_PR(Integer games_PR) {
        this.games_PR = games_PR;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
    
}
