package com.example.easyfoodapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.domain.entites.mealEntity.Meal
import com.example.easyfoodapp.databinding.ActivityMealsBinding
import com.example.easyfoodapp.fragments.HomeFragment
import com.example.easyfoodapp.viewModels.HomeViewModel
import com.example.easyfoodapp.viewModels.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsActivity : AppCompatActivity() {
    private val viewModel: MealsViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var bind: ActivityMealsBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private var isFav: Boolean = false
    private lateinit var youtubeLink: String
    private lateinit var meal: Meal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMealsBinding.inflate(layoutInflater)
        setContentView(bind.root)
        getMealInfoFromIntent()
        onLoading()
        viewModel.getMealById(mealId)
        observeMeal()
        setInfoInViews()
        observeFavMeals()

        onYoutubeImgClick()

        addToFav()
        removeFromFav()
    }

    private fun addToFav() {
        bind.favIconFalse.setOnClickListener {
            viewModel.addMealToFavorites(meal)
            bind.favIconFalse.visibility = View.INVISIBLE
            bind.favIconTrue.visibility = View.VISIBLE
            Toast.makeText(this, "Meal Saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFav() {
        bind.favIconTrue.setOnClickListener {
            viewModel.deleteMealFromFavorites(meal)
            bind.favIconFalse.visibility = View.VISIBLE
            bind.favIconTrue.visibility = View.INVISIBLE
            Toast.makeText(this, "Meal removed", Toast.LENGTH_SHORT).show()
        }
    }


    private fun onYoutubeImgClick() {
        bind.imageYoutube.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(i)
        }
    }

    private fun observeFavMeals() {
        homeViewModel.observeFavMeals().observe(this) { mealList ->
            mealList.forEach {
                if (it.idMeal == mealId) {
                    isFav = true
                }
            }
        }
    }

    private fun observeMeal() = viewModel.observeMealById().observe(this) {
        this.meal = it
        meal?.let { mealF ->
            bind.category.text = "Category:  ${mealF.strCategory}"
            bind.location.text = "Location:  ${mealF.strArea}"
            bind.description.text = mealF.strInstructions
            youtubeLink = mealF.strYoutube!!
            Log.d("zaza is meal null", "${mealF.strMeal}")
            onResponse()
        }
    }

    private fun setInfoInViews() {
        Glide.with(applicationContext).load(mealThumb).into(bind.mealImgDetail)
        bind.collapsingToolbar.title = mealName
    }

    private fun getMealInfoFromIntent() {
        val i = intent
        mealId = i.getStringExtra(HomeFragment.selectedMeal_id)!!
        mealName = i.getStringExtra(HomeFragment.selectedMeal_name)!!
        mealThumb = i.getStringExtra(HomeFragment.selectedMeal_thumb)!!
    }

    private fun onLoading() {
        bind.progressBar.visibility = View.VISIBLE
        bind.category.visibility = View.INVISIBLE
        bind.location.visibility = View.INVISIBLE
        bind.instructionText.visibility = View.INVISIBLE
        bind.description.visibility = View.INVISIBLE
        bind.favIconFalse.visibility = View.INVISIBLE
        bind.favIconTrue.visibility = View.INVISIBLE
    }

    private fun onResponse() {
        Log.d("zaza isFav on response", "$isFav")
        bind.progressBar.visibility = View.INVISIBLE
        bind.category.visibility = View.VISIBLE
        bind.location.visibility = View.VISIBLE
        bind.instructionText.visibility = View.VISIBLE
        bind.description.visibility = View.VISIBLE
        when (isFav) {
            true -> {
                bind.favIconFalse.visibility = View.INVISIBLE
                bind.favIconTrue.visibility = View.VISIBLE
            }

            false -> {
                bind.favIconFalse.visibility = View.VISIBLE
                bind.favIconTrue.visibility = View.INVISIBLE
            }
        }
    }
}