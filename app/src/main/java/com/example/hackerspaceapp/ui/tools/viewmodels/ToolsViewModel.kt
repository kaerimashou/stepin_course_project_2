package com.example.hackerspaceapp.ui.tools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackerspaceapp.ui.app.App
import com.example.hackerspaceapp.ui.tools.model.Tool

class ToolsViewModel : ViewModel() {
    var liveDataTools = App.getInstance().toolsDao.getAllLiveData()
}