package com.oneOfMyProjectByTeTe.todo

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.oneOfMyProjectByTeTe.todo.Adapter.RecycleListAdapter
import com.oneOfMyProjectByTeTe.todo.Model.ToDoItem

class ListViewModel : ViewModel() {
    private val database = Firebase.database
    private val myRef = database.getReference("To Do List")
    private var list = arrayListOf<ToDoItem>()

    init {
        retrieveData()
    }

    private val _data = MutableLiveData<ArrayList<ToDoItem>>()
    val data: LiveData<ArrayList<ToDoItem>> get() = _data

    private fun retrieveData() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (item in snapshot.children) {
                    val task = item?.getValue(ToDoItem::class.java)
                    if (task != null) {
                        list.add(task)
                    }


                }
                _data.value = list

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun addTask(id: String?, title: String, body: String) {
        if (id.isNullOrEmpty()) {
            val id = myRef.push().key.toString()
            val item = ToDoItem(id, title, body)
            if (!nullCheck(title, body)) {
                myRef.child(id).setValue(item).addOnSuccessListener {

                }
            }
        } else {
            val item = ToDoItem(id, title, body)
            myRef.child(id).setValue(item).addOnSuccessListener {

            }
        }
    }

    fun deleteTask(id:String){
        myRef.child(id).removeValue()
    }

    private fun nullCheck(title: String, body: String): Boolean {
        return title.isNullOrBlank() && body.isNullOrBlank()

    }
}