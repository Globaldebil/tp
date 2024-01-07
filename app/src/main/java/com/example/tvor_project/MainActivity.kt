package com.example.tvor_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.tvor_project.databinding.ActivityMainBinding
import com.example.tvor_project.di.ApiModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch

import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Finder())
//        val query = "Ктбо3-"
//        GlobalScope.launch(Dispatchers.Main){
//            val api = ApiModule.provideApi()
//            try {
//                val res_search = api.getSearchResult(query)
//                println(res_search)
//                val table_ebat = api.getRaspisanie("1.html", 2)
//                println(table_ebat)
//            } catch(e:Exception){
//                println(e.message);
//            }
//        }
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