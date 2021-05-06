package com.example.hackerspaceapp.ui.projects

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackerspaceapp.R
import com.example.hackerspaceapp.ui.projects.adapters.ProjectAdapter

class ProjectsFragment : Fragment() {

    private lateinit var projectsViewModel: ProjectsViewModel
    private val adapter= ProjectAdapter()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        projectsViewModel=ViewModelProvider(this).get(ProjectsViewModel::class.java)
        projectsViewModel.liveDataProject.observe(viewLifecycleOwner, {
            adapter.setItems(it)
            Log.d("Privet",it.size.toString())
        })

        val listProject=view.findViewById<RecyclerView>(R.id.listProjects)
        listProject.setHasFixedSize(true)
        listProject.layoutManager=LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        listProject.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        listProject.adapter=adapter
    }
}