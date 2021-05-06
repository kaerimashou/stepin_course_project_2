package com.example.hackerspaceapp.ui.projects.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.example.hackerspaceapp.R
import com.example.hackerspaceapp.ui.projects.ProjectDetailsActivity
import com.example.hackerspaceapp.ui.projects.model.Project
import kotlinx.android.synthetic.main.item_project.view.*
import java.text.SimpleDateFormat
import java.util.*

class ProjectAdapter: RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    private var lists:SortedList<Project> = SortedList(Project::class.java,object : SortedListAdapterCallback<Project>(this){
        override fun compare(o1: Project?, o2: Project?): Int {
            return (o1!!.doc- o2!!.doc).toInt()
        }

        override fun areContentsTheSame(oldItem: Project?, newItem: Project?): Boolean {
            return oldItem==newItem
        }

        override fun areItemsTheSame(item1: Project?, item2: Project?): Boolean {
            return item1!!.id== item2!!.id
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemChanged(position,count)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position,count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position,count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition,toPosition)
        }
    })


    class ProjectViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var project: Project
        var projectTitle: TextView = itemView.findViewById(R.id.projectText)

        init{
            itemView.setOnClickListener{
                ProjectDetailsActivity.start(caller = itemView.context as Activity, project = project)
            }
        }

        fun bind(project:Project){
            this.project=project
            projectTitle.text=project.projname
        }
    }

    fun setItems(projects:List<Project>){
        lists.replaceAll(projects)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_project,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(lists[position])
    }

    override fun getItemCount(): Int {
        return lists.size()
    }
}