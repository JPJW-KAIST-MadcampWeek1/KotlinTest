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

var token = 0
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNav : BottomNavigationView


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    token++


//    if(token == 0) {
//        loadFragment(SecondFragment())
//    }


    val temp = intent.getStringExtra("finished")
    if(temp == "true"){
        loadFragment(FourthFragment())
    }






    bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
    bottomNav.setOnItemSelectedListener {
        when (it.itemId) {
            R.id.contacts -> {
                loadFragment(SecondFragment())
                true
            }
            R.id.gallery -> {
                loadFragment(ThirdFragment())
                true
            }
            R.id.newyear -> {
                loadFragment(FourthFragment())
                true
            }
            else -> {
                // Add handling for other cases here
                false // Return false or handle the default case accordingly
            }
        }

    }
}
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
    public fun setUpRecyclerView() {
        val images = listOf<Image>(
            Image("Images 1", R.drawable.img1),
            Image("Images 2", R.drawable.img2),
            Image("Images 3", R.drawable.img3),
            Image("Images 4", R.drawable.img4),
            Image("Images 5", R.drawable.img5)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.imagesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ImageAdapter(this, images)
    }
//    public fun setUpRecyclerView_second() {
//        val images = listOf<Image>(
//            Image("Images 1", R.drawable.img1),
//            Image("Images 2", R.drawable.img2),
//            Image("Images 3", R.drawable.img3),
//            Image("Images 4", R.drawable.img4),
//            Image("Images 5", R.drawable.img5)
//        )
//
//        val recyclerView = findViewById<RecyclerView>(R.id.imagesRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = ImageAdapter(this, images)
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}