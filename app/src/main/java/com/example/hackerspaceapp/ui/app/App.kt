package com.example.hackerspaceapp.ui.app

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.hackerspaceapp.ui.projects.data.ProjectDAO
import com.example.hackerspaceapp.ui.tools.data.ToolQueueDao
import com.example.hackerspaceapp.ui.tools.data.ToolsDao
import com.example.hackerspaceapp.ui.tools.model.ToolQueue
import java.text.SimpleDateFormat
import java.util.*

class App: Application() {
    private lateinit var db:AppDatabase

    lateinit var projectDao:ProjectDAO
        private set
    lateinit var toolsDao: ToolsDao
        private set
    lateinit var toolQueueDao: ToolQueueDao
        private set
//    lateinit var database:FirebaseDatabase
//    lateinit var toolsDB:DatabaseReference
//    lateinit var toolQueueDB:DatabaseReference
//    lateinit var projectsDB:DatabaseReference

    companion object{
        @JvmStatic
        private lateinit var instance:App

        @JvmStatic
        fun getInstance(): App {
            return instance
        }
    }

    private val formatter=SimpleDateFormat("dd/MM")
    private val calendar=Calendar.getInstance().time


    override fun onCreate() {
        db=databaseBuilder(applicationContext,AppDatabase::class.java,"db-app").allowMainThreadQueries().build()
        instance=this
        super.onCreate()
        projectDao=db.projectDao
        toolsDao=db.toolDao
        toolQueueDao=db.toolQueueDao
        for(i in 0..6){
            var c=Calendar.getInstance().time
            c.date+=i
            var date=formatter.format(c)
            if(toolQueueDao.findByTime(date).isEmpty()){
                for(tool in toolsDao.getAll()){
                    toolQueueDao.insert(ToolQueue(tool.id,date,1))
                    toolQueueDao.insert(ToolQueue(tool.id,date,2))
                    toolQueueDao.insert(ToolQueue(tool.id,date,3))
                    toolQueueDao.insert(ToolQueue(tool.id,date,4))
                    toolQueueDao.insert(ToolQueue(tool.id,date,5))
                    toolQueueDao.insert(ToolQueue(tool.id,date,6))
                    toolQueueDao.insert(ToolQueue(tool.id,date,7))
                    toolQueueDao.insert(ToolQueue(tool.id,date,8))
                    toolQueueDao.insert(ToolQueue(tool.id,date,9))
                }
            }
        }
    }
}