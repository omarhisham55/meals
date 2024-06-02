package com.example.easyfoodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.entites.categoryEntity.Category
import com.example.domain.entites.categoryEntity.CategoryList
import com.example.easyfoodapp.R
import com.example.easyfoodapp.activities.CategoriesListActivity
import com.example.easyfoodapp.activities.MainActivity
import com.example.easyfoodapp.adapters.CategoriesAdapter
import com.example.easyfoodapp.adapters.GridSpacingItemDecoration
import com.example.easyfoodapp.databinding.FragmentCategoriesBinding
import com.example.easyfoodapp.databinding.FragmentHomeBinding
import com.example.easyfoodapp.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private lateinit var bind: FragmentCategoriesBinding
    private lateinit var categoryAdapter: CategoriesAdapter
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = (activity as MainActivity).homeViewModel
        categoryAdapter = CategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentCategoriesBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCategories()
        onCategoryClick()
    }

    private fun onCategoryClick() {
        categoryAdapter.onItemClick = {
            val i = Intent(activity, CategoriesListActivity::class.java)
            i.putExtra(HomeFragment.selected_category_name, it.strCategory)
            i.putExtra(
                HomeFragment.selected_category_count,
                homeViewModel.categories.value!!.size.toString()
            )
            startActivity(i)
        }
    }

    private fun observeCategories() {
        homeViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.setItems(categories)
            initCategoryRecyclerView()
        }
    }

    private fun initCategoryRecyclerView() {
        val spacing = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp)
        bind.categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            bind.categoryRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, spacing, true))
            adapter = categoryAdapter
        }

    }


}