package com.example.easyfoodapp.fragments.bottomSheets

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.easyfoodapp.R
import com.example.easyfoodapp.activities.MainActivity
import com.example.easyfoodapp.activities.MealsActivity
import com.example.easyfoodapp.databinding.FragmentMealBottomSheetBinding
import com.example.easyfoodapp.fragments.HomeFragment
import com.example.easyfoodapp.viewModels.HomeViewModel
import com.example.easyfoodapp.viewModels.MealsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.qualifiers.ApplicationContext

private const val MEAL_ID = "param"

class MealBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var bind: FragmentMealBottomSheetBinding
    private lateinit var homeViewModel: HomeViewModel
    private var mealId: String? = null
    private var mealName: String? = null
    private var mealThumb: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = (activity as MainActivity).homeViewModel
        arguments?.let {
            mealId = it.getString(MEAL_ID)
            mealId?.let { id -> homeViewModel.getMealById(id) }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentMealBottomSheetBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealObserver()
        onBottomSheetClick()
    }

    private fun onBottomSheetClick() {
        bind.bottomSheetLayout.setOnClickListener {
            mealName?.let { name ->
                mealThumb?.let { thumb ->
                    val i = Intent(activity, MealsActivity::class.java)
                    i.apply {
                        putExtra(HomeFragment.selectedMeal_id, mealId)
                        putExtra(HomeFragment.selectedMeal_name, name)
                        putExtra(HomeFragment.selectedMeal_thumb, thumb)
                    }
                    startActivity(i)
                }
            }
        }
    }

    private fun mealObserver() {
        homeViewModel.bottomSheetMeal.observe(viewLifecycleOwner) { meal ->
            bind.areaBtmSheet.text = meal.strArea
            bind.categoryBtmSheet.text = meal.strCategory
            bind.titleBtmSheet.text = meal.strMeal
            Glide.with(this).load(meal.strMealThumb).into(bind.imgBottomSheet)

            mealName = meal.strMeal
            mealThumb = meal.strMealThumb
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(mealId: String) = MealBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(MEAL_ID, mealId)
            }
        }
    }
}