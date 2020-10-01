package com.pdk.dicoding.kade.data.api.response

import com.google.gson.annotations.SerializedName
import com.pdk.dicoding.kade.data.model.Team


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class TeamResponse(
    @SerializedName("teams")
    val teams: List<Team>
)