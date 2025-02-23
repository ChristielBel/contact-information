package com.example.contact_information

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var etSurname: EditText
    private lateinit var etName: EditText
    private lateinit var etPatronymic: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        etSurname = findViewById(R.id.etSurname)
        etName = findViewById(R.id.etName)
        etPatronymic = findViewById(R.id.etPatronymic)
        etPhone = findViewById(R.id.etPhone)
        btnSave = findViewById(R.id.btnSave)

        val surname = intent.getStringExtra("surname") ?: ""
        val name = intent.getStringExtra("name") ?: ""
        val patronymic = intent.getStringExtra("patronymic") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""

        etSurname.setText(surname)
        etName.setText(name)
        etPatronymic.setText(patronymic)
        etPhone.setText(phone)

        btnSave.setOnClickListener {
            val updatedInfo = Information(
                etSurname.text.toString(),
                etName.text.toString(),
                etPatronymic.text.toString(),
                etPhone.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("surname", updatedInfo.surname)
            resultIntent.putExtra("name", updatedInfo.name)
            resultIntent.putExtra("patronymic", updatedInfo.patronymic)
            resultIntent.putExtra("phone", updatedInfo.phone)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
