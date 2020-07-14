package com.pdk.dicoding.kade.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by Budi Ardianata on 14/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
@Parcelize
class LeagueDetail(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable