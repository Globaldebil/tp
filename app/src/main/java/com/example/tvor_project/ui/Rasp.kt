package com.example.tvor_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tvor_project.MAIN
import com.example.tvor_project.R
import com.example.tvor_project.databinding.FragmentFinderBinding
import com.example.tvor_project.databinding.FragmentRaspBinding
import com.example.tvor_project.di.ApiModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class Rasp : Fragment() {
    private lateinit var binding: FragmentRaspBinding
    private var week = 1
    private lateinit var group : String
    private lateinit var textView : TextView
    val api = ApiModule.provideApi()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRaspBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = binding.textWeek
        val textViewg = binding.grName
        val buttonNext = binding.buttonNext
        val bottonPrev = binding.buttonPrev

        bottonPrev.isEnabled = false
        group = arguments?.getString("group").toString()


        buttonNext.setOnClickListener {
            week++
            if(week>1) bottonPrev.isEnabled = true
            getAPI()
        }
        bottonPrev.setOnClickListener {
            week--
            if(week==1) bottonPrev.isEnabled = false
            getAPI()
        }
    }
    fun getAPI(){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val res_search = api.getRaspisanie(group, week)
                textView.setText("Неделя "+ res_search.table.week.toString())
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}