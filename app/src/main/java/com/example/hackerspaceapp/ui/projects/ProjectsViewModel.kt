package com.example.hackerspaceapp.ui.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackerspaceapp.ui.app.App
import com.example.hackerspaceapp.ui.projects.model.Project

class ProjectsViewModel : ViewModel() {
    val liveDataProject: LiveData<List<Project>> = App.getInstance().projectDao.getAllLiveData()
}