package com.example.hackerspaceapp.ui.tools.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.example.hackerspaceapp.R
import com.example.hackerspaceapp.ui.app.App
import com.example.hackerspaceapp.ui.tools.model.ToolQueue
import kotlinx.android.synthetic.main.item_schedule.view.*

class ScheduleAdapter:RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    var listQueue:SortedList<ToolQueue> = SortedList(ToolQueue::class.java,object:SortedListAdapterCallback<ToolQueue>(this){
        override fun compare(o1: ToolQueue?, o2: ToolQueue?): Int {
            return o1!!.id-o2!!.id
        }

        override fun areContentsTheSame(oldItem: ToolQueue?, newItem: ToolQueue?): Boolean {
            return oldItem==newItem
        }

        override fun areItemsTheSame(item1: ToolQueue?, item2: ToolQueue?): Boolean {
            return item1!!.id==item2!!.id && item1!!.isReserved==item2!!.isReserved
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

    class ScheduleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var queue:ToolQueue
        var time:TextView = itemView.findViewById(R.id.timeQueue)
        var checkIn: Button = itemView.findViewById(R.id.checkInQueue)
        private var silentUpdate:Boolean=false

        init{
            checkIn.setOnClickListener{
                queue.isReserved=!queue.isReserved
                App.getInstance().toolQueueDao.update(queue)
                update(queue)
            }
        }
        private fun update(queue: ToolQueue){
            if(queue.isReserved){
                checkIn.text="Booked"
            }else{
                checkIn.text="Sign up"
            }
        }

        fun bind(queue:ToolQueue){
            this.queue=queue
            when(queue.timeID){
                1->time.text="10:00-11:00"
                2->time.text="11:00-12:00"
                3->time.text="12:00-13:00"
                4->time.text="13:00-14:00"
                5->time.text="14:00-15:00"
                6->time.text="15:00-16:00"
                7->time.text="16:00-17:00"
                8->time.text="17:00-18:00"
                9->time.text="18:00-19:00"
            }
            //checkIn.isEnabled=!queue.isReserved
            update(queue)
        }

        private fun updateStrokeOut(){
            if(queue.isReserved){
                time.paintFlags=(time.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
            }else{
                time.paintFlags=(time.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
            }
        }
    }

    fun setItems(list:List<ToolQueue>){
        listQueue.replaceAll(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_schedule,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(listQueue[position])
    }

    override fun getItemCount(): Int {
        return listQueue.size()
    }
}