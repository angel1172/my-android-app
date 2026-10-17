package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextInput = findViewById(R.id.editTextInput)

        findViewById<Button>(R.id.btnOpenSecond).setOnClickListener{
            val text = editTextInput.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, "Введите текст!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("EXTRA_TEXT", text)
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnCallFriend).setOnClickListener {
            val phone = editTextInput.text.toString().trim()
            if (phone.isEmpty()) {
                Toast.makeText(this, "Введите номер телефона!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val cleanPhone = phone.filter { it.isDigit() || it == '+' || it == '*' }
            if (cleanPhone.isEmpty()) {
                Toast.makeText(this, "Неправильный номер", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:\$cleanPhone")
            }
            if (dialIntent.resolveActivity(packageManager) != null) {
                startActivity(dialIntent)
            } else {
                Toast.makeText(this, "Нет приложения для звонков", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnShareText).setOnClickListener {
            val text = editTextInput.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, "Введите текст для отправки!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
            val chooser = Intent.createChooser(shareIntent, "Поделиться через...")
            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            } else {
                Toast.makeText(this, "Нет приложений для обмена", Toast.LENGTH_SHORT).show()
            }
        }
    }
}