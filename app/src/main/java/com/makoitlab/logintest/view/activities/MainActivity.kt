package com.makoitlab.logintest.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider

import com.google.firebase.firestore.Query
import com.makoitlab.logintest.R
import com.makoitlab.logintest.databinding.ActivityMainBinding
import com.makoitlab.logintest.viewmodel.LogInViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var logInViewModel: LogInViewModel
    private lateinit var notesQuery: Query

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        logInViewModel = ViewModelProvider(this).get(LogInViewModel::class.java)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id : Int = item.itemId
        when(id){
            R.id.action_logout -> {
                logInViewModel.logOut()
                finish()
                startActivity(Intent(this, StartupActivity::class.java))
            }

        }
        return  super.onOptionsItemSelected(item)
    }

}