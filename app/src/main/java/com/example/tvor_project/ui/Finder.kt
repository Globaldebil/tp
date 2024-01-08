package com.example.tvor_project.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvor_project.MAIN
import com.example.tvor_project.R
import com.example.tvor_project.data_classes.Choice
import com.example.tvor_project.databinding.FragmentFinderBinding
import com.example.tvor_project.di.ApiModule
import com.example.tvor_project.recycler.CustomRecyclerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.PrintWriter


class Finder : Fragment(), CustomRecyclerAdapter.Listener {
    private lateinit var binding: FragmentFinderBinding
    private var adapter: CustomRecyclerAdapter = CustomRecyclerAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater, container)
        val editText = binding.searchBar
        val recyclerView = binding.searchList
        val textView = binding.textGroup
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
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
                                adapter.createAll(res_search)
                            }
                        } catch (e: Exception) {
                            println(e.message);
                        }
                    }
                } else {
                    textView.text = query
                    textView.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            val api = ApiModule.provideApi()
                            try {
                                val res_search = api.getSearchGroup(query)
                                val bundle = Bundle()
                                bundle.putString("group",res_search.table.group)

                                MAIN.navController.navigate(R.id.action_finder_to_rasp,bundle)
                            } catch (e: Exception) {
                                println(e.message);
                            }
                        }
                    }
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

    override fun onClick(choice: Choice) {
        Toast.makeText(requireContext(), choice.name, Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putString("group", choice.group)
        MAIN.navController.navigate(R.id.action_finder_to_rasp,bundle)
    }
    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentFinderBinding.inflate(inflater,container,false)
    }
}