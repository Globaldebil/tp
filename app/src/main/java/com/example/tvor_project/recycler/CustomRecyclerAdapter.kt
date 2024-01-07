package com.example.tvor_project.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvor_project.R
import com.example.tvor_project.data_classes.Choice
import com.example.tvor_project.data_classes.Choices

class CustomRecyclerAdapter(
    val listener: Listener
) : RecyclerView
.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    private var names = ArrayList<Choice>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val largeTextView: TextView = itemView.findViewById(R.id.search_res)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView.text = names[position].name
        holder.largeTextView.setOnClickListener {
            listener.onClick(names[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun createAll(choices: Choices){
        deleter()
        choices.choices.forEach {
            this.names.add(it)
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleter(){
        names.removeAll(names.toSet())
    }


    override fun getItemCount() = names.size
    interface Listener{
        fun onClick(choice: Choice)
    }
}