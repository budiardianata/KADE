package com.pdk.dicoding.kade.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pdk.dicoding.kade.data.repository.TeamRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class EventDetailViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: TeamRepository = TeamRepository(application)

    val listBadge: MutableLiveData<Array<String>> = MutableLiveData()

    fun getBadges(home: String, away: String) {
        viewModelScope.launch {
            try {
                val homeBadge = async { repository.getTeam(home) }
                val awayBadge = async { repository.getTeam(away) }
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
    }

}