package com.pdk.dicoding.kade.data.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 18/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
data class CountriesResponse(
    @SerializedName("countries")
    val countries: List<Country>
)

@Parcelize
class Country(
    @SerializedName("name_en")
    val name: String
) : Parcelable