package com.farmingdale.ht_individualproject2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

//Table stores answers and money
@Entity
data class HistoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val correctAnswers: String,
    val money: Int

)

@Dao
interface HistoryDao{
    //returns all history items
    @Query("SELECT * FROM HistoryItem")
    fun getAllHistoryItems(): LiveData<List<HistoryItem>>

    //saves item
    @Insert
    fun saveMenuItem(item: HistoryItem)

    //deletes all records from HistoryItem Table
    @Query("DELETE FROM HistoryItem")
    fun deleteAllHistoryItems()
}
//Database
@Database(version = 1, entities = [HistoryItem::class])
abstract class HistoryDatabase : RoomDatabase(){
    abstract fun historydao() : HistoryDao
}