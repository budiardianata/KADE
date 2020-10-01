package com.pdk.dicoding.kade.ui.viewmodels

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.data.model.Event
import com.pdk.dicoding.kade.data.repository.EventRepository
import com.pdk.dicoding.kade.data.repository.TeamRepository
import com.pdk.dicoding.kade.utils.CustomHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class EventDetailViewModel(application: Application) : AndroidViewModel(application) {

    private var teamRepository: TeamRepository = TeamRepository(application)
    private var eventRepository: EventRepository = EventRepository(application)
    private val _favMessage = MutableLiveData<CustomHandler<@StringRes Int>>()
    val listBadge: MutableLiveData<Array<String>> = MutableLiveData()

    val isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
    val favMessage: LiveData<CustomHandler<Int>> get() = _favMessage

    fun getBadges(home: String, away: String) = viewModelScope.launch {
        try {
            val homeBadge = async { teamRepository.getTeam(home) }
            val awayBadge = async { teamRepository.getTeam(away) }
            listBadge.postValue(
                arrayOf(
                    homeBadge.await().teams[0].badge,
                    awayBadge.await().teams[0].badge
                )
            )
        } catch (exception: Exception) {
            println(exception.message)
        }
    }


    fun getIsFavorite(id: String) = viewModelScope.launch {
        eventRepository.checkIsFav(id).collect {
            isFavorite.postValue(it == 1)
        }
    }

    fun updateFavorite(event: Event) {
        if (isFavorite.value == false) {
            addToFavorite(event)
        } else {
            removeFromFavorite(event)
        }
    }

    private fun addToFavorite(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        val add = async { eventRepository.addFavorite(event) }
        if (add.await() > 0) {
            setFavMessage(R.string.favorite_add)
        } else {
            setFavMessage(R.string.favorite_failed)
        }
    }

    private fun removeFromFavorite(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        val remove = async { eventRepository.deleteFavorite(event) }
        if (remove.await() != 0) {
            setFavMessage(R.string.favorite_remove)
        } else {
            setFavMessage(R.string.favorite_failed)
        }
    }

    private fun setFavMessage(@StringRes message: Int) {
        _favMessage.postValue(CustomHandler(message))
    }
}