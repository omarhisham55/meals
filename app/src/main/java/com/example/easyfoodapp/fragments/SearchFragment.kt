package com.example.easyfoodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyfoodapp.activities.MainActivity
import com.example.easyfoodapp.activities.MealsActivity
import com.example.easyfoodapp.adapters.SearchMealsAdapter
import com.example.easyfoodapp.databinding.FragmentSearchBinding
import com.example.easyfoodapp.viewModels.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private lateinit var bind: FragmentSearchBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var searchAdapter: SearchMealsAdapter
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).homeViewModel
        searchAdapter = SearchMealsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentSearchBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.searchTextQuery.addTextChangedListener { query ->
            searchJob?.cancel()
            Log.d("zaza start job", query.toString())
            searchJob = lifecycleScope.launch {
                delay(500)
                if (query.toString().isNotEmpty()) viewModel.getMealsBySearch(query.toString())
                Log.d("zaza start search", query.toString())

            }
            observeSearchList()
        }
        onBackClick()
        onItemClick()
    }

    private fun observeSearchList() {
        viewModel.searchList.observe(viewLifecycleOwner) {
            if (it.meals == null) return@observe
            searchAdapter.setItems(it.meals)
            initRecyclerView()

        }
    }

    private fun initRecyclerView() {
        bind.searchMealsRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter
        }
    }

    private fun onItemClick() {
        searchAdapter.onItemClick = { meal ->
            val i = Intent(activity, MealsActivity::class.java)
            i.putExtra(HomeFragment.selectedMeal_id, meal.idMeal)
            i.putExtra(HomeFragment.selectedMeal_name, meal.strMeal)
            i.putExtra(HomeFragment.selectedMeal_thumb, meal.strMealThumb)
            startActivity(i)
        }
    }

    private fun onBackClick() {
        bind.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}