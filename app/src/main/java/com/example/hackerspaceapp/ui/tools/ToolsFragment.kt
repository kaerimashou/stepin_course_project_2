package com.example.hackerspaceapp.ui.tools

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackerspaceapp.R
import com.example.hackerspaceapp.ui.app.App
import com.example.hackerspaceapp.ui.tools.adapters.ScheduleAdapter
import com.example.hackerspaceapp.ui.tools.viewmodels.ToolQueueViewModel
import com.example.hackerspaceapp.ui.tools.viewmodels.ToolsViewModel
import kotlinx.android.synthetic.main.fragment_tools.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ToolsFragment : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel
    private lateinit var toolQueueViewModel:ToolQueueViewModel
    private var adapter=ScheduleAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        toolsViewModel =
                ViewModelProvider(this).get(ToolsViewModel::class.java)
        toolQueueViewModel =
                ViewModelProvider(this).get(ToolQueueViewModel::class.java)
        return inflater.inflate(R.layout.fragment_tools, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        spinnersCreation()
        spinnerTool?.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                toolQueueViewModel.getLiveData(App.getInstance().toolsDao.findByName(parent!!.getItemAtPosition(position).toString()).id,spinnerDate.selectedItem.toString()).observe(viewLifecycleOwner,{
                    adapter.setItems(it)
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinnerDate?.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                toolQueueViewModel.getLiveData(App.getInstance().toolsDao.findByName(spinnerTool.selectedItem.toString()).id,parent!!.getItemAtPosition(position).toString()).observe(viewLifecycleOwner,{
                    adapter.setItems(it)
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        val listQueues=view.findViewById<RecyclerView>(R.id.listQueue)
        listQueues.adapter=adapter
        listQueues.setHasFixedSize(true)
        listQueues.layoutManager= LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        listQueues.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

    }

    private fun spinnersCreation(){
        val formatter=SimpleDateFormat("dd/MM")
        val calendar=formatter.format(Calendar.getInstance().time)
        val arrayDate= arrayListOf(calendar)
        for(i in 1..6){
            var c=Calendar.getInstance().time
            c.date+=i
            arrayDate.add(formatter.format(c))
        }
        val adapterDate=ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayDate
        )
        spinnerDate.adapter=adapterDate

        toolsViewModel.liveDataTools.observe(viewLifecycleOwner,{
            var liveDataArray= it
            var arrayNames:ArrayList<String> = arrayListOf()
            var arrayImages:ArrayList<Int> = arrayListOf()
            for(t in liveDataArray){
                arrayNames.add(t.name)
                arrayImages.add(t.image)
            }
            val adapterTools=CustomSpinner(
                    requireContext(),
                    arrayImages,
                    arrayNames
            )
            spinnerTool.adapter=adapterTools
        })


    }
}