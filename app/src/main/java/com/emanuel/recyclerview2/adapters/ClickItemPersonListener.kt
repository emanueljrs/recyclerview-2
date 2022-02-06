package com.emanuel.recyclerview2.adapters

import com.emanuel.recyclerview2.models.Person

interface ClickItemPersonListener {

    fun onItemPersonClickListener(person: Person)

    fun onItemPersonLongClickListener(person: Person)

}