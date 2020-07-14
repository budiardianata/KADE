package com.pdk.dicoding.kade.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Budi Ardianata on 13/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
@Parcelize
class League (val id: Int?,
              val name: String?,
              val description: String?,
              val image: Int):Parcelable