package com.example.easyfoodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.easyfoodapp.databinding.PopularItemBinding

class MostPopularAdapter() : RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {
    private var items = ArrayList<MealsByCategory>()
    lateinit var onItemClick: ((MealsByCategory) -> Unit)
    var onItemLongClick: ((MealsByCategory) -> Unit)? = null

    fun setItems(items: ArrayList<MealsByCategory>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            PopularItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClick.invoke(items[position])
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(items[position])
            true
        }
    }

    class ViewHolder(private val itemBinding: PopularItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(mealsByCategory: MealsByCategory) {
            Glide.with(itemBinding.root.context)
                .load(mealsByCategory.strMealThumb)
                .into(itemBinding.imgPopularItem)
        }
    }
}