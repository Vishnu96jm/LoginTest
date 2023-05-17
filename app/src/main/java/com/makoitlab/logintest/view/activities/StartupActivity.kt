package com.makoitlab.logintest.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import com.makoitlab.logintest.databinding.ActivityStartupBinding
import com.makoitlab.logintest.viewmodel.LogInViewModel

class StartupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartupBinding
    private lateinit var viewModel: LogInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)

        binding.startNowBtn.setOnClickListener {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }


        viewModel.getUserData().observe(this,
            Observer<FirebaseUser?> { firebaseUser ->
                if (firebaseUser != null) {
                    onLoginSuccess()
                }
            })
    }

    private fun onLoginSuccess() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}