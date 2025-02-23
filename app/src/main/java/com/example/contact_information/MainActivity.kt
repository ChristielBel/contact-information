package com.example.contact_information

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val LOG_TAG = "com.example.lab2_MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd: Button
    private lateinit var btnChange: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var contactTextView: TextView

    private val infoViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG, "MainActivity создан")

        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)
        btnChange = findViewById(R.id.btnChange)
        nextButton = findViewById(R.id.ibNext)
        prevButton = findViewById(R.id.ibPrev)
        contactTextView = findViewById(R.id.contactTextView)

        updateInfo()

        btnAdd.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            addContactLauncher.launch(intent)
        }

        btnChange.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            val current = infoViewModel.currentFullInfo.split("\n")

            intent.putExtra("surname", current[0].split(" ")[0])
            intent.putExtra("name", current[0].split(" ")[1].removeSuffix("."))
            intent.putExtra("patronymic", current[0].split(" ")[2].removeSuffix("."))
            intent.putExtra("phone", current[1])

            editContactLauncher.launch(intent)
        }

        nextButton.setOnClickListener {
            infoViewModel.moveToNext()
            updateInfo()
        }

        prevButton.setOnClickListener {
            infoViewModel.moveToPrev()
            updateInfo()
        }
    }

    private val addContactLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val newContact = Information(
                        data.getStringExtra("surname") ?: "",
                        data.getStringExtra("name") ?: "",
                        data.getStringExtra("patronymic") ?: "",
                        data.getStringExtra("phone") ?: ""
                    )
                    infoViewModel.addContact(newContact)
                    updateInfo()
                }
            }
        }

    private val editContactLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val updatedContact = Information(
                        data.getStringExtra("surname") ?: "",
                        data.getStringExtra("name") ?: "",
                        data.getStringExtra("patronymic") ?: "",
                        data.getStringExtra("phone") ?: ""
                    )
                    infoViewModel.updateCurrentContact(updatedContact)
                    updateInfo()
                }
            }
        }

    private fun updateInfo() {
        contactTextView.text = infoViewModel.currentShortInfo
    }
}
