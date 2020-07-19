package com.pdk.dicoding.kade.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
@Parcelize
class Team(
    @SerializedName("idTeam")
    val id: String,
    @SerializedName("strTeam")
    val name: String,
    @SerializedName("strDescriptionEN")
    val description: String,
    @SerializedName("intFormedYear")
    val formedYear: String,
    @SerializedName("strInstagram")
    val instagram: String,
    @SerializedName("strTwitter")
    val twitter: String,
    @SerializedName("strWebsite")
    val website: String,
    @SerializedName("strYoutube")
    val youtube: String,
    @SerializedName("strCountry")
    val country: String,
    @SerializedName("strGender")
    val gender: String,
    @SerializedName("strTeamBadge")
    val badge: String,
    @SerializedName("strTeamJersey")
    val jersey: String,
    @SerializedName("strStadium")
    val stadiumName: String,
    @SerializedName("intStadiumCapacity")
    val StadiumCapacity: String,
    @SerializedName("strStadiumDescription")
    val stadiumDescription: String,
    @SerializedName("strStadiumLocation")
    val stadiumLocation: String,
    @SerializedName("strStadiumThumb")
    val stadiumThumb: String
) : Parcelable