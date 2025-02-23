package com.example.contact_information

import androidx.annotation.StringRes

data class Information(
    val surname: String,
    val name: String,
    val patronymic: String,
    val phone: String
) {
    val shortInfo
        get() = surname +
                (if (name.length > 0) {
                    " ${name.subSequence(0, 1)}. "
                } else "") +
                (if (patronymic.length > 0) {
                    "${patronymic.subSequence(0, 1)}. "
                } else "") + "\n" + phone

    val fullInfo: String
        get() = "$surname $name $patronymic\n$phone"
}