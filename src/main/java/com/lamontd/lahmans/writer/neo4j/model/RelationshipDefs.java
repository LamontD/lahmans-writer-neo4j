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

/**
 *
 * @author lamontdozierjr
 */
public interface RelationshipDefs {
    static final String PLAYER_APPEARANCE = "MADE_APPEARANCE";
    static final String APPEARANCE_TEAM = "APPEARED_FOR";
    static final String PLAYER_HONOR_WINNER = "WON_HONOR";
    static final String PLAYER_HONOR_AWARD = "OF_AWARD";
    static final String PLAYER_HONOR_LEAGUE = "AWARDED_FOR";
    static final String TEAM_LEAGUE_PARTICIPANT = "PLAYED_IN";
    static final String TEAM_LEAGUE_WINNER = "WON";
    static final String TEAM_WORLDSERIES_PARTICIPANT = "PLAYED_IN";
    static final String TEAM_WORLDSERIES_WINNER = "WON";
    static final String TEAM_DIVISION_PARTICIPANT = "PLAYED_IN";
    static final String TEAM_DIVISION_WINNER = "WON";
    static final String TEAM_FRANCHISE = "BELONGS_TO";
    static final String DIVISION_LEAGUE = "MEMBER_OF";
}
