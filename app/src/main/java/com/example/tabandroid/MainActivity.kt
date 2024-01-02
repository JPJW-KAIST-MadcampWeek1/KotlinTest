package com.example.tabandroid

import android.content.Intent
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
import com.example.tabandroid.ideal_worldcup.StartActivity


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNav : BottomNavigationView
    private val secondFragment = SecondFragment()
    private val thirdFragment = ThirdFragment()
    private val fourthFragment = FourthFragment()
    private val fifthFragment = FifthFragment()
    private var hasStartBeenShown = false



override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
    binding = ActivityMainBinding.inflate(layoutInflater)
//    setContentView(binding.root)
    setContentView(binding.root)

    val fragmentToLoad = intent.getStringExtra("loadFragment")
    if (fragmentToLoad == "FourthFragment") {
        loadFragment(FourthFragment())
    } else {
        // If no specific command, load the default fragment or perform other actions
    }


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
                if (!hasStartBeenShown) {
                    // If the start activity hasn't been shown yet, show it and set the flag
                    val startIntent = Intent(this, StartActivity::class.java)
                    startActivity(startIntent)
                    hasStartBeenShown = true
                } else {
                    // If it's already been shown, load the FourthFragment
                    loadFragment(FourthFragment())
                }

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


    // Helper function to convert dp to pixels
    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }


}