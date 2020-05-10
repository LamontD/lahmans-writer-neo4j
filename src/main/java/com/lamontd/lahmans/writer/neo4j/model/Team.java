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
import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author lamontdozierjr
 */
@NodeEntity
public class Team {

    @Id
    private Integer id;
    private Integer year;
    private Integer games;
    private Integer wins;
    private Integer losses;
    private Integer teamRank;

    @JsonIgnoreProperties("team")
    @Relationship(type = RelationshipDefs.APPEARANCE_TEAM, direction = Relationship.INCOMING)
    private List<Appearance> appearances;

    @JsonIgnoreProperties("teams")
    @Relationship(type = RelationshipDefs.TEAM_FRANCHISE)
    private Franchise franchise;

    @JsonIgnoreProperties("teams")
    @Relationship(type = RelationshipDefs.TEAM_DIVISION_PARTICIPANT)
    private Division divisionPlayedIn;

    @JsonIgnoreProperties("teams")
    @Relationship(type = RelationshipDefs.TEAM_DIVISION_WINNER)
    private Division divisionWon;

    @JsonIgnoreProperties("teams")
    @Relationship(type = RelationshipDefs.TEAM_LEAGUE_PARTICIPANT)
    private League leagueParticipant;

    @JsonIgnoreProperties("teams")
    @Relationship(type = RelationshipDefs.TEAM_LEAGUE_WINNER)
    private League leagueWon;

    @JsonIgnoreProperties({"participants", "winner"})
    @Relationship(type = RelationshipDefs.TEAM_WORLDSERIES_PARTICIPANT)
    private WorldSeries worldSeriesPlayedIn;

    @JsonIgnoreProperties({"participants", "winner"})
    @Relationship(type = RelationshipDefs.TEAM_WORLDSERIES_WINNER)
    private WorldSeries worldSeriesWon;

    public Team() {
    }

    public Team(Integer id) {
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

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public List<Appearance> getAppearances() {
        return appearances;
    }

    public void setAppearances(List<Appearance> appearances) {
        this.appearances = appearances;
    }

    public Division getDivisionPlayedIn() {
        return divisionPlayedIn;
    }

    public void setDivisionPlayedIn(Division divisionPlayedIn) {
        this.divisionPlayedIn = divisionPlayedIn;
    }

    public Division getDivisionWon() {
        return divisionWon;
    }

    public void setDivisionWon(Division divisionWon) {
        this.divisionWon = divisionWon;
    }

    public League getLeagueParticipant() {
        return leagueParticipant;
    }

    public void setLeagueParticipant(League leagueParticipant) {
        this.leagueParticipant = leagueParticipant;
    }

    public League getLeagueWon() {
        return leagueWon;
    }

    public void setLeagueWon(League leagueWon) {
        this.leagueWon = leagueWon;
    }

    public WorldSeries getWorldSeriesPlayedIn() {
        return worldSeriesPlayedIn;
    }

    public void setWorldSeriesPlayedIn(WorldSeries worldSeriesPlayedIn) {
        this.worldSeriesPlayedIn = worldSeriesPlayedIn;
    }

    public WorldSeries getWorldSeriesWon() {
        return worldSeriesWon;
    }

    public void setWorldSeriesWon(WorldSeries worldSeriesWon) {
        this.worldSeriesWon = worldSeriesWon;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public Integer getTeamRank() {
        return teamRank;
    }

    public void setTeamRank(Integer teamRank) {
        this.teamRank = teamRank;
    }

}
