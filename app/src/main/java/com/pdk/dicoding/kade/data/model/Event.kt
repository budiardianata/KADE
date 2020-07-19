package com.pdk.dicoding.kade.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 15/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
@Parcelize
class Event(
    @SerializedName("idEvent")
    val id: String,
    @SerializedName("strEvent")
    val eventName: String,
    @SerializedName("dateEvent")
    val eventDate: String,
    @SerializedName("strTime")
    val eventTime: String,
    @SerializedName("strTimeLocal")
    val eventTimeLocale: String?,

    @SerializedName("idLeague")
    val leagueId: String,
    @SerializedName("strLeague")
    val leagueName: String,
    @SerializedName("strSport")
    val leagueType: String,

    @SerializedName("idHomeTeam")
    val idHome: String,
    @SerializedName("strHomeTeam")
    val homeTeam: String,
    @SerializedName("strHomeTeamBadge")
    var homeBadge: String?,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("strHomeFormation")
    val homeFormation: String?,
    @SerializedName("strHomeGoalDetails")
    val homeGoalDetails: String?,
    @SerializedName("strHomeLineupDefense")
    val homeLineupDefense: String?,
    @SerializedName("strHomeLineupForward")
    val homeLineupForward: String?,
    @SerializedName("strHomeLineupGoalkeeper")
    val homeLineupGoalkeeper: String?,
    @SerializedName("strHomeLineupMidfield")
    val homeLineupMidfield: String?,
    @SerializedName("strHomeLineupSubstitutes")
    val homeLineupSubstitutes: String?,

    @SerializedName("idAwayTeam")
    val awayId: String,
    @SerializedName("strAwayTeam")
    val awayTeam: String,
    @SerializedName("strAwayTeamBadge")
    var awayBadge: String?,
    @SerializedName("intAwayScore")
    val awayScore: String?,
    @SerializedName("strAwayFormation")
    val awayFormation: String?,
    @SerializedName("strAwayGoalDetails")
    val awayGoalDetails: String?,
    @SerializedName("strAwayLineupDefense")
    val awayLineupDefense: String?,
    @SerializedName("strAwayLineupForward")
    val awayLineupForward: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    val awayLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupMidfield")
    val awayLineupMidfield: String?,
    @SerializedName("strAwayLineupSubstitutes")
    val awayLineupSubstitutes: String?
) : Parcelable