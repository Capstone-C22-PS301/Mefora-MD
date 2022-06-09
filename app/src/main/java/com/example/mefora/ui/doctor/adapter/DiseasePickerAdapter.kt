package com.example.mefora.ui.doctor.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mefora.api.model.GetPatientListResponseItem

class DiseasePickerAdapter(private var data: List<GetPatientListResponseItem>) :
    RecyclerView.Adapter<DiseasePickerAdapter.DiseasePickerViewHolder>() {


    fun setData(data: List<GetPatientListResponseItem>) {
        val diffCallback = DiseasePickerDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseasePickerViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: DiseasePickerViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class DiseasePickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}

class DiseasePickerDiffCallback(
    private val oldList: List<GetPatientListResponseItem>,
    private val newList: List<GetPatientListResponseItem>
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
