package com.pdk.dicoding.kade.data.local

import androidx.room.*
import com.pdk.dicoding.kade.data.model.Event
import kotlinx.coroutines.flow.Flow


/**
 * Created by Budi Ardianata on 01/10/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
@Dao
interface FootballDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event): Long

    @Query("SELECT * from events_table ORDER BY id DESC")
    fun getEventFavoriteList(): Flow<List<Event>>

    @Query("SELECT EXISTS(SELECT 1 FROM events_table WHERE id = :id)")
    fun checkIsFavEvent(id: String): Flow<Int>

    @Delete
    suspend fun deleteEvent(event: Event): Int
}