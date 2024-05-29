package com.example.easyfoodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entites.categoryEntity.Category
import com.example.easyfoodapp.databinding.CategoryItemBinding

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    private var items = ArrayList<Category>()
    lateinit var onItemClick: ((Category) -> Unit)

    fun setItems(items: ArrayList<Category>) {
        this.items = items
        //for changing the whole list
        //if you want to a specific change use notifyItemChanged(position)
        notifyDataSetChanged()
        /*other notifiers {
            notifyItemRangeChanged(start, count)
            notifyItemInserted(position)
            notifyItemRemoved(position)
        */

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CategoryItemBinding.inflate(
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

    inner class ViewHolder(private val itemBinding: CategoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: Category) {
            Glide.with(itemBinding.root.context)
                .load(category.strCategoryThumb)
                .into(itemBinding.imgCategory)
            itemBinding.titleCategory.text = category.strCategory
        }
    }
}