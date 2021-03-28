package com.example.rodem

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        btn_logout.setOnClickListener {
            Toast.makeText(this, "Bye!!", Toast.LENGTH_LONG).show()
            finish()
        }

    }

}