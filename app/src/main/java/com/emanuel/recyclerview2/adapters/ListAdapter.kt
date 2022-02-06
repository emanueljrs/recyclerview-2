package com.emanuel.recyclerview2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.recyclerview2.databinding.ItemListBinding
import com.emanuel.recyclerview2.models.Person
import java.util.*

class ListAdapter(
    private val personList: MutableList<Person>?,
    private val listener: ClickItemPersonListener
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private lateinit var binding: ItemListBinding
    //private val list: MutableList<Person>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ListViewHolder(
            itemBinding = binding,
            listener = listener,
            list = personList
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(personList!![position])
    }

    override fun getItemCount(): Int = personList?.size ?: 0

//    fun removePerson(person: Person) {
//        notifyItemRemoved(personList.indexOf(person))
//        personList.remove(person)
//    }

    fun removeAt(position: Int) {
        personList?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun swap(origin: Int, destiny: Int) {
        Collections.swap(
            personList,
            origin,
            destiny
        )
        notifyItemMoved(
            origin,
            destiny
        )
    }

    class ListViewHolder(
        itemBinding: ItemListBinding,
        listener: ClickItemPersonListener,
        list: List<Person>?
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val textName = itemBinding.itemTextName
        private val textDate = itemBinding.itemTextDate
        private val switchPerson = itemBinding.switchPerson

        fun bind(person: Person) {
            textName.text = person.name
            textDate.text = person.birthDate
            switchPerson.isChecked = person.check
        }

        init {
            itemBinding.root.setOnClickListener {
                listener.onItemPersonClickListener(list!![adapterPosition])
            }

            itemBinding.root.setOnLongClickListener {
                listener.onItemPersonLongClickListener(list!![adapterPosition])
                true
            }
        }


    }

}