package com.example.hackerspaceapp.ui.app

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hackerspaceapp.ui.converters.Converters
import com.example.hackerspaceapp.ui.projects.data.ProjectDAO
import com.example.hackerspaceapp.ui.projects.model.Project
import com.example.hackerspaceapp.ui.tools.data.ToolQueueDao
import com.example.hackerspaceapp.ui.tools.data.ToolsDao
import com.example.hackerspaceapp.ui.tools.model.Tool
import com.example.hackerspaceapp.ui.tools.model.ToolQueue

@Database(entities = [Project::class, Tool::class, ToolQueue::class],version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val projectDao:ProjectDAO
    abstract val toolDao:ToolsDao
    abstract val toolQueueDao:ToolQueueDao
}