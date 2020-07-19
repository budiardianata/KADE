package com.pdk.dicoding.kade.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.pdk.bfaadicoding.submission.utils.Resource
import com.pdk.dicoding.kade.data.api.ApiService
import com.pdk.dicoding.kade.data.model.League
import com.pdk.dicoding.kade.data.repository.LeagueRepository
import com.pdk.dicoding.kade.utils.Utils
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 * Created by Budi Ardianata on 15/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class LeagueViewModel(application: Application) : AndroidViewModel(application) {
    private val leagueRepository: LeagueRepository = LeagueRepository(ApiService(application))

    val listCountry: MutableLiveData<Array<String>> = MutableLiveData()

    var country: MutableLiveData<String> = MutableLiveData()
        set(value) {
            if (field == value) {
                return
            }
            field = value
        }

    init {
        fetchCountry()
        country.value = Utils.DEFAULT_COUNTRY
    }

    private fun fetchCountry() = viewModelScope.launch {
        try {
            val countryResponse = async { leagueRepository.getCountries() }
            val countries = countryResponse.await().countries
            listCountry.postValue(Array(countries.size) { countries[it].name })
        } catch (exception: Exception) {
            println(exception.message)
        }
    }

    val listLeague: LiveData<Resource<List<League>>> =
        country.switchMap { leagueRepository.getListLeague(it) }


}