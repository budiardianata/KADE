package com.pdk.dicoding.kade.data.api.response

import com.google.gson.annotations.SerializedName
import com.pdk.dicoding.kade.data.model.Event


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
data class EventResponse(
    @SerializedName("event")
    val events: List<Event>?
)