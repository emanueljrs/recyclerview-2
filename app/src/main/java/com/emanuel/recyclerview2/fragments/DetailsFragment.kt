package com.emanuel.recyclerview2.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.emanuel.recyclerview2.R
import com.emanuel.recyclerview2.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        with(binding) {
            textName.text = args.itemPerson.name
            textBirthday.text = args.itemPerson.birthDate
            textSwitch.text = args.itemPerson.check.toString()
        }

    }
}