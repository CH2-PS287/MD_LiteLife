package com.example.litelife.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.litelife.data.response.FoodResponse
import com.example.litelife.data.response.FoodResponseItem
import com.example.litelife.databinding.ContainerFoodExerciseBinding

class FoodAdapter : ListAdapter<FoodResponse, FoodAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val list = ArrayList<FoodResponseItem>()

    fun setList(users: List<FoodResponseItem>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ContainerFoodExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: FoodResponseItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(food)
            }
            binding.apply {
                tvfoodName.text = food.name
                tvCaloriesnumber.text = food.calorie
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ContainerFoodExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodResponse>() {
            override fun areItemsTheSame(
                oldItem: FoodResponse,
                newItem: FoodResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FoodResponse,
                newItem: FoodResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: FoodResponseItem)
    }
}