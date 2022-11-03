package com.example.datechnologies.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.datechnologies.R
import com.example.datechnologies.data.LoginResponse
import com.example.datechnologies.model.RapptrViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.HttpException
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val viewModel = RapptrViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle("Login")
        setupActionBar()
        login_linear_layout.background.alpha = 20
        login_activity_login_button.setOnClickListener { processLogin() }
    }

    private fun setupActionBar(){
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)
        }
    }
    fun processLogin(){
        var email = email_edit_text.text.toString()
        var password = password_edit_text.text.toString()
        if(validateFields(email,password)) {
            viewModel.attemptLogin(email, password)
                viewModel.loginResponse.observe(this){response->
                    buildDialogBox(response.body()!!.code,response.body()!!.message)
                }
        }
    }


    fun validateFields(email:String,password:String):Boolean{
        return when{
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter an email")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter a password")
                false
            }else ->{
                true
            }
        }
    }

    fun showErrorSnackBar(error:String){
        val snackBar = Snackbar.make(
            findViewById(android.R.id.content),
            error,Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(
            this, R.color.snackbar_error_color
        ))
        snackBar.show()
    }

    fun buildDialogBox(code:String,message:String){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(code)
        .setMessage("${message}\n The response took ${viewModel.responseTime.toString()} milliseconds")
        .setCancelable(false)
            .setPositiveButton("Ok"){_,_-> navigateHome()}
        val alert = dialogBuilder.create()
        alert.show()

    }
    fun navigateHome(){
        startActivity(Intent(this, HomeActivity::class.java))
    }
}