package com.example.truss.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.truss.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


val regLink = findViewById<TextView>(R.id.reg_link)
      val backBtn = findViewById<ImageButton>(R.id.back_btn)
      val interestbtn = findViewById<Button>(R.id.interestbtn)

        regLink.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent);
        }

interestbtn.setOnClickListener {
    val interest = Intent(this, InterestActivity::class.java);
    startActivity(interest)
}

        backBtn.setOnClickListener {

            onBackPressed()
        }
    }

}
