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

open class SignUp : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var lastName: EditText
    private  lateinit var userName: EditText
     private  lateinit var signUp: ImageView
     private lateinit var pasward: EditText
     private lateinit var haveAccount: ImageView
      val sharedPrefFile = "data"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        name = findViewById(R.id.name)
        lastName = findViewById(R.id.lastName)
        userName = findViewById(R.id.username)
        pasward = findViewById(R.id.password)
        haveAccount = findViewById(R.id.haveAccounts)
        signUp = findViewById(R.id.signIn)


        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        signUp.setOnClickListener {
        if (name.text==null||lastName.text==null||userName.text==null||pasward.text==null){
            Toast.makeText(this, "Please Fill all Fields", Toast.LENGTH_LONG).show()
        }
        else if (pasward.text.length<6){
            Toast.makeText(this, "password cannot be less then 6 character",Toast.LENGTH_LONG ).show()
        }
        else
        {
            // save the user data in the local Storage

            editor.putString("username",userName.text.toString())
            editor.putString("password",pasward.text.toString())
            editor.apply()
            editor.commit()
            val inten = Intent(this, SignIn::class.java)
            startActivity(inten)
            finish()
        }
        }
        haveAccount.setOnClickListener {
            val inten = Intent(this, SignIn::class.java)
            startActivity(inten)
            finish()
        }

    }
}