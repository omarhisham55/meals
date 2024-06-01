package com.example.easyfoodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyfoodapp.R
import com.example.easyfoodapp.activities.MealsActivity
import com.example.easyfoodapp.adapters.FavoritesAdapter
import com.example.easyfoodapp.databinding.FragmentFavoritesBinding
import com.example.easyfoodapp.viewModels.HomeViewModel
import com.example.easyfoodapp.viewModels.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var bind: FragmentFavoritesBinding
    private val viewModel: HomeViewModel by viewModels()
    private val mealViewModel: MealsViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesAdapter = FavoritesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentFavoritesBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavorites()
        onFavMealClick()
        onFavIconClick()
    }

    private fun onFavIconClick() {
        favoritesAdapter.onFavIconClick = {
            mealViewModel.deleteMealFromFavorites(it)
            Toast.makeText(context, "Meal removed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onFavMealClick() {
        favoritesAdapter.onItemClick = {
            val i = Intent(activity, MealsActivity::class.java)
            i.putExtra(HomeFragment.selectedMeal_id, it.idMeal)
            i.putExtra(HomeFragment.selectedMeal_name, it.strMeal)
            i.putExtra(HomeFragment.selectedMeal_thumb, it.strMealThumb)
            startActivity(i)
        }
    }

    private fun observeFavorites() {
        viewModel.observeFavMeals().observe(viewLifecycleOwner) { meals ->
            favoritesAdapter.setItems(meals)
            initFavoritesRecyclerView()
        }
    }

    private fun initFavoritesRecyclerView() {
        lifecycleScope.launch {
            bind.favRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = favoritesAdapter
            }
        }
    }
}