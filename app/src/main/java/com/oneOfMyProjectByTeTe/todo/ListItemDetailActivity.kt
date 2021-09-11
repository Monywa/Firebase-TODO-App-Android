package com.oneOfMyProjectByTeTe.todo

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.oneOfMyProjectByTeTe.todo.Model.ToDoItem
import com.oneOfMyProjectByTeTe.todo.databinding.ListItemDetailBinding

class ListItemDetailActivity : AppCompatActivity() {
    lateinit var binding: ListItemDetailBinding
    private val database = Firebase.database
    private val myRef = database.getReference("To Do List")

    val viewModel: ListViewModel by viewModels()

    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        id = intent?.getStringExtra("id").toString()
        val title = intent?.getStringExtra("title")
        val body = intent?.getStringExtra("body")

        binding.itemDetailTitle.setText(title)
        binding.itemDetailBody.setText(body)

    }


    override fun onBackPressed() {
        super.onBackPressed()
        saveTask()
    }

    private fun saveTask() {
        val title = binding.itemDetailTitle.text.toString()
        val body = binding.itemDetailBody.text.toString()
        viewModel.addTask(id, title, body)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            com.oneOfMyProjectByTeTe.todo.R.id.action_delete -> {
                viewModel.deleteTask(id)
                finish()
                true

            }
            R.id.home -> {
                saveTask()
                finish()
                true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.oneOfMyProjectByTeTe.todo.R.menu.menu, menu)
        return true
    }


}
