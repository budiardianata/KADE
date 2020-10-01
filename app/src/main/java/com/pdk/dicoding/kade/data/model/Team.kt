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
    @SerializedName("strTeamBadge")
    val badge: String
) : Parcelable