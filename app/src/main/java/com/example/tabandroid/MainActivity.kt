package com.example.tabandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.tabandroid.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabandroid.ideal_worldcup.FourthFragment
import androidx.recyclerview.widget.GridLayoutManager

var token = 0
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNav : BottomNavigationView
    private val secondFragment = SecondFragment()
    private val thirdFragment = ThirdFragment()
    private val fourthFragment = FourthFragment()
    private val fifthFragment = FifthFragment()

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
    binding = ActivityMainBinding.inflate(layoutInflater)
//    setContentView(binding.root)
    setContentView(R.layout.activity_main)
    token++



    val temp = intent.getStringExtra("finished")
    if(temp == "true"){
        loadFragment(FourthFragment())
    }


    bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
    bottomNav.setOnItemSelectedListener {
        when (it.itemId) {
            R.id.contacts -> {
                loadFragment(secondFragment)
                true
            }
            R.id.gallery -> {
                loadFragment(thirdFragment)
                true
            }
            R.id.newyear -> {
                loadFragment(fifthFragment)
                true
            }
            R.id.worldcup -> {
                loadFragment(fourthFragment)
                true
            }
            else -> {
                // Add handling for other cases here
                false // Return false or handle the default case accordingly
            }
        }

    }
}
    private var currentFragment: Fragment? = null

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        // Hide the current fragment, if it exists
        currentFragment?.let {
            transaction.hide(it)
        }

        // Show the existing fragment if it is already added; otherwise, add it.
        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(R.id.container, fragment)
        }

        transaction.addToBackStack(null)
        transaction.commit()

        currentFragment = fragment
    }

}