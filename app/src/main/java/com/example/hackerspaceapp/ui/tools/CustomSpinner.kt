package com.example.hackerspaceapp.ui.tools

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.hackerspaceapp.R

class CustomSpinner(var context: Context, var images: ArrayList<Int>, var names: ArrayList<String>) : BaseAdapter() {
    var inflater:LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return names.size
    }

    override fun getItem(position: Int): Any {
        return names[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view=inflater.inflate(R.layout.spinner_layout,null)
        view.findViewById<ImageView>(R.id.imageSpinner).setImageResource(images[position])
        view.findViewById<TextView>(R.id.timeSpinner).text= names[position]
        return view
    }
}