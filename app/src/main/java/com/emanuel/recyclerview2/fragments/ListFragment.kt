package com.emanuel.recyclerview2.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.recyclerview2.R
import com.emanuel.recyclerview2.adapters.ListAdapter
import com.emanuel.recyclerview2.adapters.ClickItemPersonListener
import com.emanuel.recyclerview2.databinding.FragmentListBinding
import com.emanuel.recyclerview2.models.Person

class ListFragment : Fragment(R.layout.fragment_list), ClickItemPersonListener {

    private lateinit var binding: FragmentListBinding
    private var personList: MutableList<Person>? = mutableListOf()
    private val args: ListFragmentArgs by navArgs()
    private lateinit var personAdapter: ListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        configRecycler()
        addPersonList(args.person)
        setListeners()

        val swipeHandler = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.START or ItemTouchHelper.END
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val origin = viewHolder.adapterPosition
                val destiny = target.adapterPosition
                personAdapter.swap(origin, destiny)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                personAdapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerList)
    }

    private fun configRecycler() {
        personAdapter = ListAdapter(
            personList = personList,
            listener = this
        )
        with(binding.recyclerList) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun setListeners() {
        binding.floatActionAdd.setOnClickListener {
            findNavController().navigate(R.id.from_listFragment_to_addPersonFragment)
        }
    }

    private fun addPersonList(person: Person?) {
        if(person != null) {
            personList?.add(person)
        }
    }

    private fun getMockList(): MutableList<Person>? {
        personList = mutableListOf(
            Person(
                name = "Emanuel",
                birthDate = "17/06/1991",
                check = true
            ),
            Person(
                name = "Neite",
                birthDate = "03/01/1991",
                check = false
            ),
            Person(
                name = "Alice",
                birthDate = "26/10/2007",
                check = true
            ),
            Person(
                name = "Ruth",
                birthDate = "17/03/1989",
                check = false
            ),
            Person(
                name = "Dorinha",
                birthDate = "17/08/1969",
                check = true
            ),
        )
        return personList
    }

    override fun onItemPersonClickListener(person: Person) {
        val action = ListFragmentDirections.fromListFragmentToDetailsFragment(person)
        findNavController().navigate(action)
    }

    override fun onItemPersonLongClickListener(person: Person) {
//        personAdapter.removePerson(person)
    }
}