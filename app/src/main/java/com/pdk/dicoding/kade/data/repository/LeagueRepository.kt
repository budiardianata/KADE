package com.pdk.dicoding.kade.data.repository

import androidx.lifecycle.liveData
import com.pdk.bfaadicoding.submission.utils.Resource
import com.pdk.dicoding.kade.data.api.ApiService
import com.pdk.dicoding.kade.utils.Utils
import kotlinx.coroutines.Dispatchers


/**
 * Created by Budi Ardianata on 15/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class LeagueRepository(private val apiService: ApiService) {

    suspend fun getCountries() = apiService.footballApi.listCountry()

    fun getListLeague(country: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val leaguesResponse =
                if (country == Utils.DEFAULT_COUNTRY) apiService.footballApi.listLeague(null)
                else apiService.footballApi.listLeague(country)

            if (!leaguesResponse.leagues.isNullOrEmpty()) emit(Resource.success(data = leaguesResponse.leagues))
            else emit(Resource.error(data = null, message = ""))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = "Error Occurred!"))
        }
    }
}