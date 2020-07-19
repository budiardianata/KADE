package com.pdk.dicoding.kade.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Budi Ardianata on 13/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
@Parcelize
class League(
    @SerializedName("idLeague")
    val id: String,

    @SerializedName("strLeague")
    val leagueName: String,

    @SerializedName("strBadge")
    val badge: String,

    @SerializedName("strBanner")
    val banner: String?,

    @SerializedName("strDescriptionEN")
    val description: String?,

    @SerializedName("strGender")
    val gender: String?,

    @SerializedName("strCountry")
    val country: String?,

    @SerializedName("strWebsite")
    val website: String?,

    @SerializedName("strFacebook")
    val facebook: String?,

    @SerializedName("strTwitter")
    val twitter: String?,

    @SerializedName("strYoutube")
    val youtube: String?,

    @SerializedName("strRSS")
    val rss: String?

) : Parcelable