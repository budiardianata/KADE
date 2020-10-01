package com.pdk.dicoding.kade.data.repository

import android.app.Application
import androidx.lifecycle.liveData
import com.pdk.dicoding.kade.data.api.ApiService
import com.pdk.dicoding.kade.data.local.FootballDatabase
import com.pdk.dicoding.kade.data.model.Event
import com.pdk.dicoding.kade.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */

class EventRepository(application: Application) {
    private val apiService = ApiService(application)
    private val footballDao = FootballDatabase.getDatabase(application).footballDao()
    private val strSoccer = "Soccer"

    fun searchEvent(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val leaguesResponse = apiService.footballApi.searchEvent(query)
            emit(Resource.success(data = leaguesResponse.events!!.filter { it.leagueType == strSoccer }))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ""))
        }
    }

    fun getNextEvent(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val leaguesResponse = apiService.footballApi.nextEvent(id)
            emit(Resource.success(data = leaguesResponse.events))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getPreviousEvent(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val leaguesResponse = apiService.footballApi.previousEvent(id)
            emit(Resource.success(data = leaguesResponse.events))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getEventFavorite() = footballDao.getEventFavoriteList().flowOn(Dispatchers.IO).conflate()

    fun checkIsFav(id: String) = footballDao.checkIsFavEvent(id).flowOn(Dispatchers.IO).conflate()

    suspend fun addFavorite(event: Event) = footballDao.insertEvent(event)

    suspend fun deleteFavorite(event: Event) = footballDao.deleteEvent(event)
}