package com.oneOfMyProjectByTeTe.todo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oneOfMyProjectByTeTe.todo.ListItemDetailActivity
import com.oneOfMyProjectByTeTe.todo.R
import com.oneOfMyProjectByTeTe.todo.Model.ToDoItem
class RecycleListAdapter(val context:Context, private val itemList: ArrayList<ToDoItem>):
    RecyclerView.Adapter<RecycleListAdapter.RecyclerListViewHolder>() {

    inner class RecyclerListViewHolder(private val view: View, var sendList:ToDoItem?=null):RecyclerView.ViewHolder(view){
        val title=view.findViewById<TextView>(R.id.list_title)
        val body=view.findViewById<TextView>(R.id.list_body)
        val itemClick=view.setOnClickListener {
            val intent = Intent(
                view.context,
                ListItemDetailActivity::class.java
            )
            intent.putExtra("id", sendList?.id)
            intent.putExtra("title", sendList?.title)
            intent.putExtra("body", sendList?.body)
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
           val view= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return RecyclerListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
        val item=itemList[position]
        holder.sendList=item
        holder.title.text=item.title
        holder.body.text=item.body

    }

    override fun getItemCount():Int{
        return itemList.size
    }

}