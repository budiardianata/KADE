package com.pdk.dicoding.kade.data.api

import com.pdk.dicoding.kade.data.api.response.*
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Budi Ardianata on 15/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
interface FootballApi {

    @GET("all_countries.php")
    suspend fun listCountry(): CountriesResponse

    @GET("search_all_leagues.php?s=Soccer")
    suspend fun listLeague(
        @Query("c")
        s: String?
    ): LeaguesResponse

    @GET("searchevents.php?")
    suspend fun searchEvent(
        @Query("e")
        query: String
    ): EventResponse

    @GET("eventsnextleague.php")
    suspend fun nextEvent(
        @Query("id")
        id: String
    ): EventsResponse

    @GET("eventspastleague.php")
    suspend fun previousEvent(
        @Query("id")
        id: String
    ): EventsResponse

    @GET("lookupteam.php")
    suspend fun detailTeam(
        @Query("id")
        id: String
    ): TeamResponse


}