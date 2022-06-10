package com.example.mefora.ui.doctor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mefora.api.model.GetDiseaseResponseItem
import com.example.mefora.databinding.DiseaseItemsBinding

class DiseasePatientAdapter(private val data: List<GetDiseaseResponseItem>) :
    RecyclerView.Adapter<DiseasePatientAdapter.ViewHolder>() {

    fun setData() {
        val diffCallback = DiseasePatientDiffCallback(data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DiseaseItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    class ViewHolder(val binding: DiseaseItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: GetDiseaseResponseItem) {
            binding.apply {

            }
        }
    }

    class DiseasePatientDiffCallback(
        private val oldItem: List<GetDiseaseResponseItem>,
        private val newItem: List<GetDiseaseResponseItem>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition].id == newItem[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem == newItem
        }

        override fun getOldListSize(): Int {
            return 1
        }

        override fun getNewListSize(): Int {
            return 1
        }
    }
}