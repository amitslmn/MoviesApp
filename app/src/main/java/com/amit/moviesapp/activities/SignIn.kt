package com.amit.moviesapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.moviesapp22.R

class SignIn : AppCompatActivity() {
    private lateinit var userName:EditText
    private lateinit var password:EditText
    private lateinit var donotHaveAccount:ImageView
    private lateinit var signIn:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        userName = findViewById(R.id.userNames)
        donotHaveAccount = findViewById(R.id.haveAccounts)
        password = findViewById(R.id.password)
        signIn= findViewById(R.id.signIn)

         val sharedPreferences: SharedPreferences = this.getSharedPreferences("data",
            Context.MODE_PRIVATE)
        // Retrieve the data from the local memory

        val savedUser = sharedPreferences.getString("username",null)
        val savedPassword = sharedPreferences.getString("password",null)

        signIn.setOnClickListener {
            if (savedUser==null){
                Toast.makeText(this, "Account not found", Toast.LENGTH_LONG).show()
            }
            else if (userName.text.toString()!=savedUser||password.text.toString()!=savedPassword){
                Toast.makeText(this, "Wrong username or Password", Toast.LENGTH_SHORT).show()
            }
            else{
                val inten = Intent(this, MainActivity::class.java)
                startActivity(inten)
                finish()
            }
        }
        donotHaveAccount.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }
}