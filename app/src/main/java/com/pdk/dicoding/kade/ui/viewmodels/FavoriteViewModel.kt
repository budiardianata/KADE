package com.pdk.dicoding.kade.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pdk.dicoding.kade.data.model.Event
import com.pdk.dicoding.kade.data.repository.EventRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val _eventFavorite by lazy { MutableLiveData<List<Event>>() }
    private val eventRepository = EventRepository(application)

    val eventFavorite: LiveData<List<Event>> get() = _eventFavorite

    init {
        getFavorite()
    }

    private fun getFavorite() = viewModelScope.launch {
        eventRepository.getEventFavorite().collect {
            _eventFavorite.value = it
        }
    }
}