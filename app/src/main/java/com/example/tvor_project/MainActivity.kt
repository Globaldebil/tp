package com.example.tvor_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tvor_project.databinding.ActivityMainBinding
import com.example.tvor_project.ui.Favorite
import com.example.tvor_project.ui.Finder
import com.example.tvor_project.ui.Rasp


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Finder())
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.fav -> replaceFragment(Favorite())
                R.id.rsp -> replaceFragment(Rasp())
                R.id.srch -> replaceFragment(Finder())
                else ->{

                }
            }
            true
        }
    }
    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}