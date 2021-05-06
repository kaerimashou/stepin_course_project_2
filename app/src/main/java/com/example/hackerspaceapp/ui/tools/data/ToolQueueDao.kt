package com.example.hackerspaceapp.ui.tools.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hackerspaceapp.ui.tools.model.Tool
import com.example.hackerspaceapp.ui.tools.model.ToolQueue
import java.sql.Date
import java.sql.Time
@Dao
interface ToolQueueDao {
    @Query("SELECT * FROM ToolQueue")
    fun getAll():List<ToolQueue>

    @Query("SELECT * FROM ToolQueue")
    fun getAllLiveData(): LiveData<List<ToolQueue>>

    @Query("SELECT * FROM ToolQueue WHERE toolID=:toolID AND Date=:date")
    fun getToolDateLiveData(toolID:Int,date: String):LiveData<List<ToolQueue>>

    @Query("SELECT * FROM ToolQueue WHERE id IN (:toolQueueIDs)")
    fun loadAllById(toolQueueIDs:IntArray):List<ToolQueue>

    @Query("SELECT * FROM ToolQueue WHERE id=:id LIMIT 1")
    fun findById(id:Int): ToolQueue

    @Query("SELECT * FROM ToolQueue WHERE toolID=:toolID")
    fun findByToolID(toolID:Int):List<ToolQueue>

    @Query("SELECT * FROM ToolQueue WHERE date=:date")
    fun findByTime(date:String):List<ToolQueue>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(toolQueue: ToolQueue)

    @Update
    fun update(toolQueue: ToolQueue)

    @Delete
    fun delete(toolQueue: ToolQueue)
}