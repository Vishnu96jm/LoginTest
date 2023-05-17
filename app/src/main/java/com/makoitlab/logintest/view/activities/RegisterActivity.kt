package com.makoitlab.logintest.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import androidx.lifecycle.Observer
import com.makoitlab.logintest.databinding.ActivityRegisterBinding
import com.makoitlab.logintest.utils.gone
import com.makoitlab.logintest.utils.toast
import com.makoitlab.logintest.utils.visible
import com.makoitlab.logintest.viewmodel.RegisterViewModel


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        supportActionBar?.hide()
        initUi()

        viewModel.getUser().observe(this,
            Observer<FirebaseUser?> { firebaseUser ->
                if (firebaseUser != null) {
                    onRegisterSuccess()
                }
            })
    }

    private fun initUi() {
        binding.register.setOnClickListener {
            processData(binding.emailInput.text.toString(),
                binding.passwordInput.text.toString())
        }
    }

    private fun processData(email: String, password: String) {
        if (email.isNotBlank() && password.isNotBlank()) {

            if (password.length < 8){
                toast("The password must be at least 8 characters long.")
            }else{
                viewModel.register(email, password)
            }
        } else {
            onRegisterError()
        }
    }

    private fun onRegisterSuccess() {
        binding.errorText.gone()
        finish()
    }

    private fun onRegisterError() {
        binding.errorText.visible()
    }
}