package com.example.easyfoodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.easyfoodapp.databinding.CategoryItemBinding
import com.example.easyfoodapp.databinding.CategoryListMealItemBinding

class CategoryListAdapter() : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    private var items = ArrayList<MealsByCategory>()
    lateinit var onItemClick: (MealsByCategory) -> Unit

    fun setItems(items: ArrayList<MealsByCategory>) {
        this.items = items
        //for changing the whole list
        //if you want to a specific change use notifyItemChanged(position)
        notifyDataSetChanged()/*other notifiers {
            notifyItemRangeChanged(start, count)
            notifyItemInserted(position)
            notifyItemRemoved(position)
        */

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        CategoryListMealItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClick.invoke(items[position])
        }
    }

    inner class ViewHolder(private val itemBinding: CategoryListMealItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(meals: MealsByCategory) {
            itemBinding.mealNameCategory.text = meals.strMeal
            Glide.with(itemBinding.root).load(meals.strMealThumb).into(itemBinding.mealImgCategory)
        }
    }
}