package com.emanuel.recyclerview2.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val birthDate: String,
    val check: Boolean = false
) : Parcelable
