package com.emanuel.recyclerview2.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanuel.recyclerview2.models.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListPersonVIewModel(
    private val preferences: SharedPreferences
) : ViewModel() {

    private val _personList = MutableLiveData<MutableList<Person>>()
    val personList: LiveData<MutableList<Person>>
        get() = _personList

    fun getListTarefa(): MutableList<Person> {
        val list = preferences.getString("persons", "[]")
        val turnsType = object : TypeToken<List<Person>>() {}.type
        return Gson().fromJson(list, turnsType)
    }

    private fun fetchPersons() {
        //  if ()
    }


}