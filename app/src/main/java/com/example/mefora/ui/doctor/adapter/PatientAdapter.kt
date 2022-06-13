package com.example.mefora.ui.doctor.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mefora.api.model.GetPatientListResponseItem
import com.example.mefora.databinding.PatientItemsBinding

class PatientAdapter(
    private var data: List<GetPatientListResponseItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {
    private lateinit var binding: PatientItemsBinding

    interface OnItemClickListener {
        fun onItemClick(item: GetPatientListResponseItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(getPatientListResponseItem: GetPatientListResponseItem){
        data = data.plus(getPatientListResponseItem)
        notifyDataSetChanged()
    }

    fun setData(data: List<GetPatientListResponseItem>) {
        val diffCallback = PatientAdapterDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.onBind(data[position], listener)
    }

    class PatientViewHolder(val binding: PatientItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(responseItem: GetPatientListResponseItem, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load("https://pbs.twimg.com/profile_images/1521717752464957440/zVnwuSXm_400x400.jpg")
                    .into(ivPicture)
//                tvTitle.text = responseItem.
                tvDescription.text = responseItem.patientUid
                root.setOnClickListener {
                    listener.onItemClick(responseItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PatientViewHolder(
        PatientItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
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