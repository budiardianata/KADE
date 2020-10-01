package com.pdk.dicoding.kade.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.pdk.dicoding.kade.data.model.Event
import com.pdk.dicoding.kade.data.repository.EventRepository
import com.pdk.dicoding.kade.utils.Resource


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private var eventRepository = EventRepository(application)

    var query: MutableLiveData<String> = MutableLiveData()
        set(value) {
            if (field == value) return
            else field = value
        }

    val searchResult: LiveData<Resource<List<Event>>> = query.switchMap {
        eventRepository.searchEvent(it)
    }

}