package com.rcappstudios.qualityeducation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.database.FirebaseDatabase
import com.rcappstudios.qualityeducation.databinding.ActivityMainBinding
import com.rcappstudios.qualityeducation.fragments.mentors.MentorViewMessagesActivity
import com.rcappstudios.qualityeducation.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private  var isMentor: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        supportActionBar!!.hide()
        setContentView(binding.root)
        setUpNavigationComponent()
        isMentor = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE)
            .getBoolean("isMentor", false)

//        FirebaseDatabase.getInstance()
//            .getReference("Room/-NQtlKL0du600SeiAvE9/whiteBoard")
//            .removeValue()

    }

    private fun setUpNavigationComponent(){
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setupWithNavController(getNavController())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        if(item.itemId == R.id.viewMentorMessage){
            startActivity(Intent(this, MentorViewMessagesActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(isMentor){
            menuInflater.inflate(R.menu.mentor_top_menu, menu)
        }
        return true
    }

    private fun getNavController(): NavController {
        return (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
    }

}