package com.pdk.dicoding.kade.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.pdk.dicoding.kade.data.model.Event
import com.pdk.dicoding.kade.data.repository.EventRepository
import com.pdk.dicoding.kade.utils.EventType
import com.pdk.dicoding.kade.utils.Resource


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class PrevNextViewModel(application: Application) : AndroidViewModel(application) {
    private val ids: MutableLiveData<String> = MutableLiveData()
    private var eventRepository = EventRepository(application)
    private lateinit var type: EventType

    val dataEvent: LiveData<Resource<List<Event>>> = Transformations
        .switchMap(ids) {
            when (type) {
                EventType.PREVIOUS -> {
                    eventRepository.getPreviousEvent(it)
                }
                EventType.NEXT -> {
                    eventRepository.getNextEvent(it)
                }
            }
        }

    fun setParam(id: String?, typeEvent: EventType) {
        if (ids.value == id) {
            return
        }
        ids.value = id
        type = typeEvent
    }
}