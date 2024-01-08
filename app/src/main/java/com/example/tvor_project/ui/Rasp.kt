package com.example.tvor_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.tvor_project.MAIN
import com.example.tvor_project.R
import com.example.tvor_project.data_classes.Schedule
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
    private lateinit var mon: Button
    private lateinit var tue: Button
    private lateinit var wen: Button
    private lateinit var thu: Button
    private lateinit var fri: Button
    private lateinit var sat: Button
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView

    private lateinit var week_name: TextView
    private lateinit var dy : TextView

    private lateinit var res_search: Schedule
    private lateinit var button: ImageButton
    private lateinit var textView : TextView
    private lateinit var textViewg : TextView

    private lateinit var tble: List<List<String>>
    val api = ApiModule.provideApi()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRaspBinding.inflate(inflater,container,false)
        week_name = binding.weekName
        button = binding.buttonBack
        button.setOnClickListener {
            MAIN.navController.navigate(R.id.action_rsp_to_srch)
        }
        mon = binding.monday
        tue = binding.tuesday
        wen = binding.wensday
        thu = binding.thusday
        fri = binding.friday
        sat = binding.saturday
        one = binding.first
        two = binding.second
        three = binding.third
        four = binding.four
        five = binding.five
        six = binding.six
        seven = binding.seven

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = binding.textWeek
        textViewg = binding.grName
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
        getAPI()
        mon.setOnClickListener {
            dayClick(1)
        }
        tue.setOnClickListener {
            dayClick(2)
        }
        wen.setOnClickListener {
            dayClick(3)}
        thu.setOnClickListener { dayClick(4)
        }
        fri.setOnClickListener {
            dayClick(5)
        }
        sat.setOnClickListener {
            dayClick(6)
        }

    }
    fun getAPI(){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                res_search = api.getRaspisanie(group, week)
                textViewg.text = res_search.table.name
                tble = res_search.table.table
                textView.setText("Неделя "+ res_search.table.week.toString())
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
    fun dayClick(day : Int){
        week_name.text = tble[day+1][0]
        one.text = tble[day+1][1]
        two.text = tble[day+1][2]
        three.text = tble[day+1][3]
        four.text = tble[day+1][4]
        five.text = tble[day+1][5]
        six.text = tble[day+1][6]
        seven.text = tble[day+1][7]
    }
}