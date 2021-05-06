package com.example.hackerspaceapp.ui.tools.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hackerspaceapp.ui.tools.model.Tool

@Dao
interface ToolsDao {
    @Query("SELECT * FROM Tool")
    fun getAll():List<Tool>

    @Query("SELECT * FROM Tool")
    fun getAllLiveData(): LiveData<List<Tool>>

    @Query("SELECT * FROM Tool WHERE id IN (:toolIDs)")
    fun loadAllById(toolIDs:IntArray):List<Tool>

    @Query("SELECT * FROM Tool WHERE id=:id LIMIT 1")
    fun findById(id:Int): Tool

    @Query("SELECT * FROM Tool WHERE name=:name LIMIT 1")
    fun findByName(name:String):Tool

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tool: Tool)

    @Update
    fun update(tool: Tool)

    @Delete
    fun delete(tool: Tool)
}