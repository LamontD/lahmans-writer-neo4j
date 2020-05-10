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

import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author lamontdozierjr
 */
@NodeEntity
public class WorldSeries {
    @Id
    private Integer year;
    
    @Relationship(type=RelationshipDefs.TEAM_WORLDSERIES_PARTICIPANT, direction=Relationship.INCOMING)
    private List<Team> participants;
    
    @Relationship(type=RelationshipDefs.TEAM_WORLDSERIES_WINNER, direction=Relationship.INCOMING)
    private Team winner;
    
    public WorldSeries() {}
    public WorldSeries(Integer year) { this.year = year; }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Team> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Team> participants) {
        this.participants = participants;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }
    
    
}
