package com.example.mefora.ui.patient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mefora.api.model.PredictionsItem
import com.example.mefora.databinding.ItemRowFoodBinding

class FoodAdapter(val data: List<String>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodViewHolder(
        ItemRowFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    class FoodViewHolder(val binding: ItemRowFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {
            binding.apply {
                tvFoodName.text = item
            }
        }
    }

    class FoodAdapterDiffUtil(
        private val oldData: List<PredictionsItem>,
        private val newData: List<PredictionsItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

    }

}