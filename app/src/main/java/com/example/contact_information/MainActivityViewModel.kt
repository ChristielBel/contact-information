package com.example.contact_information

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val contactList = mutableListOf(
        Information("Петрова", "Анна", "Дамировна", "+79921937977"),
        Information("Богданов", "Александр", "Михайлович", "+79782591398"),
        Information("Ершов", "Ярослав", "Максимович", "+79585709449")
    )

    private var currentIndex = 0

    val currentShortInfo: String
        get() = contactList[currentIndex].shortInfo

    val currentFullInfo: String
        get() = contactList[currentIndex].fullInfo

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % contactList.size
    }

    fun moveToPrev() {
        currentIndex = (currentIndex - 1) % contactList.size
    }

    fun addContact(info: Information) {
        contactList.add(info)
    }

    fun updateCurrentContact(info: Information) {
        if (contactList.isNotEmpty()) {
            contactList[currentIndex] = info
        }
    }
}