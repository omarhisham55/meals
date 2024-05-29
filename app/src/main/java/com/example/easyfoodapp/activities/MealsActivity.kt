package com.example.easyfoodapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.domain.entites.mealEntity.Meal
import com.example.easyfoodapp.databinding.ActivityMealsBinding
import com.example.easyfoodapp.fragments.HomeFragment
import com.example.easyfoodapp.viewModels.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsActivity : AppCompatActivity() {
    private val viewModel: MealsViewModel by viewModels()
    private lateinit var bind: ActivityMealsBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
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

        onYoutubeImgClick()
    }

    private fun onYoutubeImgClick() {
        bind.imageYoutube.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(i)
        }
    }

    private fun observeMeal() = viewModel.observeMealById().observe(this) {
        this.meal = it
        bind.category.text = "Category:  ${meal.strCategory}"
        bind.location.text = "Location:  ${meal.strArea}"
        bind.description.text = meal.strInstructions
        youtubeLink = meal.strYoutube
        onResponse()
    }

    private fun setInfoInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(bind.mealImgDetail)
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
        bind.favIcon.visibility = View.INVISIBLE
    }

    private fun onResponse() {
        bind.progressBar.visibility = View.INVISIBLE
        bind.category.visibility = View.VISIBLE
        bind.location.visibility = View.VISIBLE
        bind.instructionText.visibility = View.VISIBLE
        bind.description.visibility = View.VISIBLE
        bind.favIcon.visibility = View.VISIBLE
    }
}