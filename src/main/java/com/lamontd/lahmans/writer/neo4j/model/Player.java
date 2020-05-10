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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author lamontdozierjr
 */
@NodeEntity
public class Player {
    @Id
    private String playerId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String battingSide;
    private String throwingSide;
    private LocalDate debut;
    private LocalDate lastGame;
    
    @JsonIgnoreProperties("player")
    @Relationship(type = RelationshipDefs.PLAYER_APPEARANCE)
    private List<Appearance> appearances;
    
    public Player() {
    }
    
    public Player(String playerId) {
        this.playerId = playerId;
    }
    
    public void addAppearance(Appearance appearance) {
        if (this.appearances == null) {
            this.appearances = new ArrayList<>();
        }
        this.appearances.add(appearance);
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public String getBattingSide() {
        return battingSide;
    }

    public void setBattingSide(String battingSide) {
        this.battingSide = battingSide;
    }

    public String getThrowingSide() {
        return throwingSide;
    }

    public void setThrowingSide(String throwingSide) {
        this.throwingSide = throwingSide;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getLastGame() {
        return lastGame;
    }

    public void setLastGame(LocalDate lastGame) {
        this.lastGame = lastGame;
    }

    public List<Appearance> getAppearances() {
        return appearances;
    }

    public void setAppearances(List<Appearance> appearances) {
        this.appearances = appearances;
    }
    
    
    
}
