package com.pdk.dicoding.kade.data.repository

import android.app.Application
import com.pdk.dicoding.kade.data.api.ApiService
import com.pdk.dicoding.kade.data.api.response.TeamResponse


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class TeamRepository(application: Application) {

    private val apiService = ApiService(application)

    suspend fun getTeam(id: String): TeamResponse = apiService.footballApi.detailTeam(id)
}