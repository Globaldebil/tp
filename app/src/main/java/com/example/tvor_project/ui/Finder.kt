package com.example.tvor_project.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.tvor_project.databinding.FragmentFinderBinding
import com.example.tvor_project.di.ApiModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Finder : Fragment() {
    private lateinit var binding: FragmentFinderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater, container)
        val editText = binding.searchBar
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = editText.text.toString()
                GlobalScope.launch(Dispatchers.Main) {
                    val api = ApiModule.provideApi()
                    try {
                        val res_search = api.getSearchResult(query)
                        //TODO:Сделать адаптер для РесайклерВью
                    } catch (e: Exception) {
                        println(e.message);
                    }
        }
            }
        })
        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentFinderBinding.inflate(inflater,container,false)
    }
}