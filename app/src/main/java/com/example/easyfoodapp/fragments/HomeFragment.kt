package com.example.easyfoodapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.domain.entites.categoryEntity.Category
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.domain.entites.mealEntity.Meal
import com.example.easyfoodapp.viewModels.HomeViewModel
import com.example.easyfoodapp.activities.MealsActivity
import com.example.easyfoodapp.adapters.CategoriesAdapter
import com.example.easyfoodapp.adapters.MostPopularAdapter
import com.example.easyfoodapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private lateinit var randomMeal: Meal
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object {
        const val selectedMeal_id = "selected_meal_id"
        const val selectedMeal_name = "selected_meal_name"
        const val selectedMeal_thumb = "selected_meal_thumb"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularItemsAdapter = MostPopularAdapter()
        categoriesAdapter = CategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()

        viewModel.getPopularMeals()
        observePopularMeals()
        onPopularMealClick()

        viewModel.getCategories()
        observeCategories()
        onCategoryClick()
    }

    private fun initPopularItemsRecyclerView() {
        bind.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun initCategoryItemsRecyclerView() {
        bind.recViewCategories.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategories() {
        lifecycleScope.launch {
            viewModel.observeCategories().observe(viewLifecycleOwner) {
                categoriesAdapter.setItems(it as ArrayList<Category>)
                initCategoryItemsRecyclerView()
            }
        }
    }

    private fun observePopularMeals() {
        lifecycleScope.launch {
            viewModel.observePopularMeals().observe(viewLifecycleOwner) {
                popularItemsAdapter.setItems(it as ArrayList<MealsByCategory>)
                initPopularItemsRecyclerView()
            }
        }
    }

    private fun observeRandomMeal() {
        viewModel.observeRandomMeal().observe(
            viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment).load(meal.strMealThumb).into(bind.imgRandomMeal)
            this.randomMeal = meal
        }
    }

    private fun onPopularMealClick() {
        popularItemsAdapter.onItemClick = {
            val i = Intent(activity, MealsActivity::class.java)
            i.putExtra(selectedMeal_id, it.idMeal)
            i.putExtra(selectedMeal_name, it.strMeal)
            i.putExtra(selectedMeal_thumb, it.strMealThumb)
            startActivity(i)
        }
    }

    private fun onRandomMealClick() {
        bind.randomMealCard.setOnClickListener {
            val i = Intent(activity, MealsActivity::class.java)
            i.putExtra(selectedMeal_id, randomMeal.idMeal)
            i.putExtra(selectedMeal_name, randomMeal.strMeal)
            i.putExtra(selectedMeal_thumb, randomMeal.strMealThumb)
            startActivity(i)
        }
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = {
            viewModel.getPopularMeals(it.strCategory)
        }
    }


}