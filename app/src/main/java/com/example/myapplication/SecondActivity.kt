package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val text = intent.getStringExtra("EXTRA_TEXT") ?: ""
        findViewById<TextView>(R.id.textViewResult).text = if (text.isNotEmpty()) {
            "Полученный текст: \$text"
        } else {
            "Текст не передан"
        }
    }
}