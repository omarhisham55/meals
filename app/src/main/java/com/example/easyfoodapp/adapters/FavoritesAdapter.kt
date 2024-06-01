package com.example.easyfoodapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entites.mealEntity.Meal
import com.example.easyfoodapp.R
import com.example.easyfoodapp.databinding.FavoriteItemBinding
import com.example.easyfoodapp.viewModels.MealsViewModel

class FavoritesAdapter(
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {
    private var items = listOf<Meal>()
    lateinit var onItemClick: (Meal) -> Unit
    lateinit var onFavIconClick: (Meal) -> Unit

//    private val diff = object : DiffUtil.ItemCallback<Meal>() {
//        override fun areItemsTheSame(
//            oldItem: Meal, newItem: Meal
//        ): Boolean {
//            return oldItem.idMeal == newItem.idMeal
//        }
//
//        override fun areContentsTheSame(
//            oldItem: Meal, newItem: Meal
//        ): Boolean {
//            return oldItem == newItem
//        }
//    }
//    val differ = AsyncListDiffer(this, diff)

    fun setItems(items: List<Meal>) {
        this.items = items
        //for changing the whole list
        //if you want to a specific change use notifyItemChanged(position)
        notifyDataSetChanged()/*other notifiers {
            notifyItemRangeChanged(start, count)
            notifyItemRangeChangedItemInserted(position)
        */
    }

    private fun editList(position: Int) {
        notifyItemInserted(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        FavoriteItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { onItemClick.invoke(items[position]) }
        holder.itemView.findViewById<ImageView>(R.id.fav_icon_fragment).setOnClickListener {
            onFavIconClick.invoke(items[position])
            editList(position)
        }

    }

    inner class ViewHolder(private val itemBinding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(meal: Meal) {
            Glide.with(itemBinding.root).load(meal.strMealThumb).into(itemBinding.favImg)
            itemBinding.favName.text = meal.strMeal
            itemBinding.favCategory.text = meal.strCategory
        }
    }
}