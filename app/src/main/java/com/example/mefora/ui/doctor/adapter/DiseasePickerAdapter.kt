package com.example.mefora.ui.doctor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mefora.api.model.GetDiseaseResponseItem
import com.example.mefora.api.model.GetPatientListResponseItem
import com.example.mefora.databinding.DiseaseItemsBinding

class DiseasePickerAdapter(private var data: List<GetDiseaseResponseItem>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<DiseasePickerAdapter.DiseasePickerViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(item: GetDiseaseResponseItem)
    }

    fun setData(data: List<GetDiseaseResponseItem>) {
        val diffCallback = DiseasePickerDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DiseasePickerViewHolder(
            DiseaseItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DiseasePickerViewHolder, position: Int) {
        holder.onBind(data[position], listener)
    }

    class DiseasePickerViewHolder(val binding: DiseaseItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(getDiseaseResponseItem: GetDiseaseResponseItem, listener: OnItemClickListener) {
            binding.apply {
                tvDiseaseName.text = getDiseaseResponseItem.diseaseName
                root.setOnClickListener {
                    listener.onItemClick(getDiseaseResponseItem)
                }
            }
        }
    }
}

class DiseasePickerDiffCallback(
    private val oldList: List<GetDiseaseResponseItem>,
    private val newList: List<GetDiseaseResponseItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
