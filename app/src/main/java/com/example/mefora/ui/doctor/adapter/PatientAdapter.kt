package com.example.mefora.ui.doctor.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mefora.api.model.GetPatientListResponseItem

class PatientAdapter(private var data: List<GetPatientListResponseItem>) :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    fun setData(data: List<GetPatientListResponseItem>) {
        val diffCallback = PatientAdapterDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class PatientViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        TODO("Not yet implemented")
    }
}

class PatientAdapterDiffCallback(
    private val oldList: List<GetPatientListResponseItem>,
    private val newList: List<GetPatientListResponseItem>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}