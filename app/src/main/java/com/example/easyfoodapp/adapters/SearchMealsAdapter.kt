package com.example.easyfoodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entites.mealEntity.Meal
import com.example.easyfoodapp.databinding.SearchMealItemBinding

class SearchMealsAdapter() : RecyclerView.Adapter<SearchMealsAdapter.ViewHolder>() {
    private var items = listOf<Meal>()
    lateinit var onItemClick: (Meal) -> Unit

    fun setItems(items: List<Meal>) {
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
        SearchMealItemBinding.inflate(
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

    inner class ViewHolder(private val itemBinding: SearchMealItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(meal: Meal) {
            Glide.with(itemBinding.root).load(meal.strMealThumb).into(itemBinding.searchMealImg)
            itemBinding.searchMealTitle.text = meal.strMeal
            itemBinding.searchMealCategory.text = meal.strCategory
            itemBinding.searchMealArea.text = meal.strArea
        }
    }
}