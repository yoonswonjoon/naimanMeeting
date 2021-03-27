package com.example.Rodem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.Rodem.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this@MainActivity, SubActivity::class.java)
        btn_test.setOnClickListener {
            Toast.makeText(this, "Hello!!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)



    }
}