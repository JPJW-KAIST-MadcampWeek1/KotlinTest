package com.example.tabandroid



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView



@Suppress("DEPRECATION")
class ContactAdapter (private val contactList: ArrayList<Contacts>) : RecyclerView.Adapter<ContactAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contacts,parent,false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val contact: Contacts = contactList.get(curPos)
                Toast.makeText(
                    parent.context,
                    "이름 : ${contact.name}\n 전화번호 : ${contact.number}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = contactList[position].name
        holder.number.text = contactList[position].number
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val number = itemView.findViewById<TextView>(R.id.tv_number)
    }

}




















