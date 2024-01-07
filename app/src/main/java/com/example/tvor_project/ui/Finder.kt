package com.example.tvor_project.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvor_project.databinding.FragmentFinderBinding
import com.example.tvor_project.di.ApiModule
import com.example.tvor_project.recycler.CustomRecyclerAdapter
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
        val recyclerView = binding.searchList
        val textView = binding.textGroup
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = editText.text.toString()
                val query_check = query.contains("Ктбо", ignoreCase = true) && (query.length >= 7)
                if (!query_check) {
                    textView.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    GlobalScope.launch(Dispatchers.Main) {
                        val api = ApiModule.provideApi()
                        try {
                            val res_search = api.getSearchResult(query)
                            if (res_search.choices.size != 1) {
                                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                                recyclerView.adapter = CustomRecyclerAdapter(res_search)
                            }
                        } catch (e: Exception) {
                            println(e.message);
                        }
                    }
                } else {
                    textView.text = query
                    textView.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentFinderBinding.inflate(inflater,container,false)
    }
}