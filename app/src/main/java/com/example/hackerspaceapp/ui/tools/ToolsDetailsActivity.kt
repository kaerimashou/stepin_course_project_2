package com.example.hackerspaceapp.ui.tools

import android.app.Activity
import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hackerspaceapp.R
import com.example.hackerspaceapp.ui.projects.ProjectDetailsActivity
import com.example.hackerspaceapp.ui.projects.model.Project
import com.example.hackerspaceapp.ui.tools.model.Tool
import kotlinx.android.synthetic.main.activity_project_details.*

class ToolsDetailsActivity : AppCompatActivity() {
    lateinit var tool: Tool

    companion object{
        @JvmStatic
        val EXTRA_TOOL = "ToolsDetailsActivity.EXTRA_TOOL"

        @JvmStatic
        fun start(caller: Activity, tool: Tool){
            val intent = Intent(caller, ToolsDetailsActivity::class.java)
            intent.putExtra(EXTRA_TOOL,tool)
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools_details)
        tool=intent.getParcelableExtra<Tool>(EXTRA_TOOL)!!
        title = tool.name
    }
}