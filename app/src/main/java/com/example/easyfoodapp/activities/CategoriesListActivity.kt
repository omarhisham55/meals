package com.example.easyfoodapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.easyfoodapp.R
import com.example.easyfoodapp.adapters.CategoryListAdapter
import com.example.easyfoodapp.adapters.GridSpacingItemDecoration
import com.example.easyfoodapp.databinding.ActivityCategoryBinding
import com.example.easyfoodapp.fragments.HomeFragment
import com.example.easyfoodapp.viewModels.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesListActivity : AppCompatActivity() {
    private lateinit var bind: ActivityCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var categoryListAdapter: CategoryListAdapter
    private lateinit var categoryName: String
    private lateinit var categoryCount: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCategoryBinding.inflate(layoutInflater)
        categoryListAdapter = CategoryListAdapter()
        setContentView(bind.root)
        onLoading()
        getCategoryInfoFromIntent()
        observeCategoryList()
        onItemClick()

        onBackPress()

    }

    private fun onItemClick() {
        categoryListAdapter.onItemClick = {
            val i = Intent(this, MealsActivity::class.java)
            i.putExtra(HomeFragment.selectedMeal_id, it.idMeal)
            i.putExtra(HomeFragment.selectedMeal_name, it.strMeal)
            i.putExtra(HomeFragment.selectedMeal_thumb, it.strMealThumb)
            startActivity(i)
        }
    }

    private fun initCategoryRecyclerView() {
        val spacing = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp)
        bind.categoryGridview.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            bind.categoryGridview.addItemDecoration(GridSpacingItemDecoration(2, spacing, true))
            adapter = categoryListAdapter
        }
    }

    private fun observeCategoryList() {
        viewModel.categoryItems.observe(this) {
            categoryListAdapter.setItems(it as ArrayList<MealsByCategory>)
            initCategoryRecyclerView()
            onResponse()
        }
    }

    private fun setTitleInViews() {
        viewModel.getCategoryList(categoryName)
        bind.categoryGridviewTitle.text =
            getString(R.string.category_title_with_amount, categoryName, categoryCount)
    }

    private fun getCategoryInfoFromIntent() {
        val i = intent
        categoryName = i.getStringExtra(HomeFragment.selected_category_name) ?: ""
        categoryCount = i.getStringExtra(HomeFragment.selected_category_count) ?: "0"
        setTitleInViews()
    }

    private fun onLoading() {
        bind.categoryProgressBar.visibility = VISIBLE
        bind.categoryGridview.visibility = INVISIBLE
    }

    private fun onResponse() {
        bind.categoryProgressBar.visibility = INVISIBLE
        bind.categoryGridview.visibility = VISIBLE
    }

    private fun onBackPress() {
        bind.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}