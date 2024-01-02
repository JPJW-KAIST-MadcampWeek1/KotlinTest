package com.example.tabandroid



import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult

import androidx.recyclerview.widget.RecyclerView



class ContactAdapter (private val contactList: ArrayList<Contacts>) : RecyclerView.Adapter<ContactAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contacts,parent,false)
        return CustomViewHolder(view)

//                val curPos: Int = adapterPosition
//                val contact: Contacts = contactList.get(curPos)
//                Toast.makeText(
//                    parent.context,
//                    "이름 : ${contact.name}\n 전화번호 : ${contact.number}",
//                    Toast.LENGTH_SHORT
//                ).show()
            }




    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val contact = contactList[position]
        holder.name.text = contact.name
        holder.number.text = contact.number

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val delayMillis: Long = 200
            Handler().postDelayed({
                val context = holder.itemView.context
                val intent = Intent(context, ContactEditActivity::class.java).apply {
                    putExtra("user_name", contact.name)
                    putExtra("user_number", contact.number)
                }
                context.startActivity(intent)
            }, delayMillis)

//            val intent = Intent(context, ContactEditActivity::class.java).apply {
//                putExtra("user_name", contact.name)
//                putExtra("user_number", contact.number)
//            }
//            (context as Activity).startActivityForResult(intent, 101)
        }
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView= itemView.findViewById<TextView>(R.id.tv_name)
        val number: TextView = itemView.findViewById<TextView>(R.id.tv_number)
    }

}




















