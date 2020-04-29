package com.example.truss.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.truss.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//initialize components

        val loginLink = findViewById<TextView>(R.id.login_link)
        val backBtn = findViewById<ImageButton>(R.id.back_btn)

        backBtn.setOnClickListener {

            onBackPressed()
        }

        loginLink.setOnClickListener {
            val loginUpIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginUpIntent);
        }

        // Create an ArrayAdapter
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.city_list, android.R.layout.simple_list_item_2)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.setNotifyOnChange(true)
        // Apply the adapter to the spinner
        find_us.adapter = adapter
        //material_spinner.adapter = adapter
    }



}

