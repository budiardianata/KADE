package com.pdk.dicoding.kade.data.api.response

import com.google.gson.annotations.SerializedName
import com.pdk.dicoding.kade.data.model.League


/**
 * Created by Budi Ardianata on 15/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
data class LeaguesResponse(
    @SerializedName("countrys")
    val leagues: List<League>
)