package com.example.hackerspaceapp.ui.projects

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackerspaceapp.R
import com.example.hackerspaceapp.ui.models.User
import com.example.hackerspaceapp.ui.projects.model.Project
import kotlinx.android.synthetic.main.activity_project_details.*

class ProjectDetailsActivity: AppCompatActivity() {

    lateinit var project:Project

    companion object{
        @JvmStatic
        val EXTRA_PROJECT = "ProjectDetailsActivity.EXTRA_PROJECT"

        @JvmStatic
        fun start(caller:Activity,project: Project){
            val intent = Intent(caller,ProjectDetailsActivity::class.java)
            intent.putExtra(EXTRA_PROJECT,project)
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_details)

        title = "Project"
        project=intent.getParcelableExtra<Project>(EXTRA_PROJECT)!!
        projectDetailsName.text = project.projname
        projectDetailsInfo.text = project.projinfo
    }
}