package com.example.tvor_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tvor_project.R
import com.example.tvor_project.databinding.FragmentFinderBinding
import com.example.tvor_project.databinding.FragmentRaspBinding
import java.io.File

class Rasp : Fragment() {
    private lateinit var binding: FragmentRaspBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRaspBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = binding.grName
        textView.text = arguments?.getString("group")
    }

}