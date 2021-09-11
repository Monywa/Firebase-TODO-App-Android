package com.oneOfMyProjectByTeTe.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.oneOfMyProjectByTeTe.todo.Adapter.RecycleListAdapter
import com.oneOfMyProjectByTeTe.todo.Model.ToDoItem
import com.oneOfMyProjectByTeTe.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       val viewModel:ListViewModel by viewModels()


        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.data.observe(this){data->
            binding.recycler.adapter=RecycleListAdapter(this,data)

        }

        binding.buttonAdd.setOnClickListener {
            val intent= Intent(this,ListItemDetailActivity::class.java)
            startActivity(intent)
        }



    }


}