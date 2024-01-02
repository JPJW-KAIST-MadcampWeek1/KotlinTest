package com.example.tabandroid
//import androidx.navigation.fragment.navArgs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
//import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabandroid.databinding.FragmentSecondBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var contactList : ArrayList<Contacts> = arrayListOf()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {



        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        //여기서 화면 연결


        return binding.root

    }

    override fun onResume() {
        super.onResume()

        contactList.clear()

        val fileName = "contacts.json"
        val file = File(requireContext().filesDir, fileName)

        if (!file.exists()) {
            // Copy the file from assets to internal storage for future use
            requireContext().assets.open(fileName).use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }

        // Now read from the file in internal storage
        val jsonString = file.readText()

        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("contacts")

        for (i in 0 until jArray.length()) {
            val obj = jArray.getJSONObject(i)
            val name = obj.getString("name")
            val number = obj.getString("number")
            contactList.add(Contacts(name, number))
        }

        // Setup RecyclerView with the contact list
        binding.rvContact.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvContact.setHasFixedSize(true)
        binding.rvContact.adapter = ContactAdapter(contactList)
//        super.onResume()
//
////        profileList.clear()
//
//        val assets = resources.assets
//        val inputStream = assets.open("contacts.json")
//        val jsonString = inputStream.bufferedReader().use{ it.readText()}
//
//        val jObject = JSONObject(jsonString)
//        val jArray = jObject.getJSONArray("contacts")
//
//
//        for(i in 0 until jArray.length()) {
//            val obj = jArray.getJSONObject(i)
//            val name = obj.getString("name")
//            val number = obj.getString("number")
//            contactList.add(Contacts( name, number))
//        }
//
//        binding.rvContact.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//        binding.rvContact.setHasFixedSize(true)
//        binding.rvContact.adapter = ContactAdapter(contactList)
//        ContactAdapter(contactList).notifyDataSetChanged()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}