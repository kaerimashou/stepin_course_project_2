package com.example.hackerspaceapp.ui.projects.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hackerspaceapp.ui.projects.model.Project

@Dao
interface ProjectDAO {
    @Query("SELECT * FROM Project")
    fun getAll():List<Project>

    @Query("SELECT * FROM Project")
    fun getAllLiveData(): LiveData<List<Project>>

    @Query("SELECT * FROM Project WHERE id IN (:projectIDs)")
    fun loadAllById(projectIDs:IntArray):List<Project>

    @Query("SELECT * FROM Project WHERE id=:id LIMIT 1")
    fun findById(id:Int):Project

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(project: Project)

    @Update
    fun update(project:Project)

    @Delete
    fun delete(project: Project)
}