package com.emanuel.recyclerview2.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emanuel.recyclerview2.R
import com.emanuel.recyclerview2.databinding.FragmentAddPersonBinding
import com.emanuel.recyclerview2.models.Person

class AddPersonFragment : Fragment(R.layout.fragment_add_person) {

    private lateinit var binding: FragmentAddPersonBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPersonBinding.bind(view)

        setListeners()
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener {
            if (checkFieldsNames()) {
                val person = Person(
                    name = binding.editInputName.text.toString(),
                    birthDate = binding.editInputDate.text.toString()
                )
                val action = AddPersonFragmentDirections.fromAddPersonFragmentToListFragment(
                    person = person
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun checkFieldsNames(): Boolean {
        return (binding.editInputName.text.toString().isNotEmpty()
                && binding.editInputDate.text.toString().isNotEmpty())
    }
}