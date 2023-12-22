package com.example.litelife.data.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.litelife.data.response.ExerciseResponse
import com.example.litelife.data.response.ExerciseResponseItem
import com.example.litelife.databinding.ContainerExercisePlansBinding

class ExerciseAdapter : ListAdapter<ExerciseResponse, ExerciseAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val list = ArrayList<ExerciseResponseItem>()

    fun setListUser(users: List<ExerciseResponseItem>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ContainerExercisePlansBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: ExerciseResponseItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(exercise)
            }
            binding.apply {
                tvExerciseName.text = exercise.activity
                tvCaloriesnumber.text = exercise.calorie
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ContainerExercisePlansBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExerciseResponse>() {
            override fun areItemsTheSame(
                oldItem: ExerciseResponse,
                newItem: ExerciseResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ExerciseResponse,
                newItem: ExerciseResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: ExerciseResponseItem)
    }

}