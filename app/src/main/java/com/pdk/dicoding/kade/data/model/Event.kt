package com.pdk.dicoding.kade.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 15/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
@Entity(tableName = "events_table")
@Parcelize
class Event(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("idEvent") val id: String,

    @ColumnInfo(name = "eventName")
    @SerializedName("strEvent") val eventName: String,

    @ColumnInfo(name = "eventDate")
    @SerializedName("dateEvent") val eventDate: String,

    @ColumnInfo(name = "eventTime")
    @SerializedName("strTime") val eventTime: String,

    @ColumnInfo(name = "eventTimeLocale")
    @SerializedName("strTimeLocal") val eventTimeLocale: String?,

    @ColumnInfo(name = "leagueType")
    @SerializedName("strSport") val leagueType: String,

    @ColumnInfo(name = "idHome")
    @SerializedName("idHomeTeam") val idHome: String,

    @ColumnInfo(name = "homeTeam")
    @SerializedName("strHomeTeam") val homeTeam: String,

    @ColumnInfo(name = "homeBadge")
    @SerializedName("strHomeTeamBadge") var homeBadge: String?,

    @ColumnInfo(name = "homeScore")
    @SerializedName("intHomeScore") val homeScore: String?,

    @ColumnInfo(name = "homeGoalDetails")
    @SerializedName("strHomeGoalDetails") val homeGoalDetails: String?,

    @ColumnInfo(name = "homeLineupDefense")
    @SerializedName("strHomeLineupDefense") val homeLineupDefense: String?,

    @ColumnInfo(name = "homeLineupForward")
    @SerializedName("strHomeLineupForward") val homeLineupForward: String?,

    @ColumnInfo(name = "homeLineupGoalkeeper")
    @SerializedName("strHomeLineupGoalkeeper") val homeLineupGoalkeeper: String?,

    @ColumnInfo(name = "homeLineupMidfield")
    @SerializedName("strHomeLineupMidfield") val homeLineupMidfield: String?,

    @ColumnInfo(name = "homeLineupSubstitutes")
    @SerializedName("strHomeLineupSubstitutes") val homeLineupSubstitutes: String?,

    @ColumnInfo(name = "awayId")
    @SerializedName("idAwayTeam") val awayId: String,

    @ColumnInfo(name = "awayTeam")
    @SerializedName("strAwayTeam") val awayTeam: String,

    @ColumnInfo(name = "awayBadge")
    @SerializedName("strAwayTeamBadge") var awayBadge: String?,

    @ColumnInfo(name = "awayScore")
    @SerializedName("intAwayScore") val awayScore: String?,

    @ColumnInfo(name = "awayGoalDetails")
    @SerializedName("strAwayGoalDetails") val awayGoalDetails: String?,

    @ColumnInfo(name = "awayLineupDefense")
    @SerializedName("strAwayLineupDefense") val awayLineupDefense: String?,

    @ColumnInfo(name = "awayLineupForward")
    @SerializedName("strAwayLineupForward") val awayLineupForward: String?,

    @ColumnInfo(name = "awayLineupGoalkeeper")
    @SerializedName("strAwayLineupGoalkeeper") val awayLineupGoalkeeper: String?,

    @ColumnInfo(name = "awayLineupMidfield")
    @SerializedName("strAwayLineupMidfield") val awayLineupMidfield: String?,

    @ColumnInfo(name = "awayLineupSubstitutes")
    @SerializedName("strAwayLineupSubstitutes") val awayLineupSubstitutes: String?
) : Parcelable