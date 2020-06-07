package com.example.imageviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    // define the login button
    private var loginButton : Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        // assign the id of the login button

        loginButton = findViewById(R.id.loginButton)
        loginButton!!.setOnClickListener(View.OnClickListener {
            if (it!=null){
                Toast.makeText(this,"Moved to next Activity",Toast.LENGTH_LONG).show()
                val intent =Intent(this,HomePageActivity::class.java)
                startActivity(intent)
            }
        })

    }
}