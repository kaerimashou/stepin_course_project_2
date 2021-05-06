package com.example.hackerspaceapp.ui.tools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hackerspaceapp.ui.app.App
import com.example.hackerspaceapp.ui.tools.model.ToolQueue
import java.sql.Date

class ToolQueueViewModel:ViewModel() {
    var liveDataQueue= App.getInstance().toolQueueDao.getAllLiveData()

    fun getLiveData(toolID:Int,date:String): LiveData<List<ToolQueue>> {
        return App.getInstance().toolQueueDao.getToolDateLiveData(toolID,date)
    }
}