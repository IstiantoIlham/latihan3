package com.istianto.latihanjson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.istianto.latihanjson.databinding.RowItemListBinding

class LazAdapter : RecyclerView.Adapter<LazAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: RowItemListBinding) : RecyclerView.ViewHolder(binding.root)

    private var listLaz = ArrayList<Laz>()

    fun setData(list: List<Laz>?) {
        if (list == null) return
        listLaz.clear()
        listLaz.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = listLaz[position].organization_name
            tvScope.text = listLaz[position].scope
            tvHeadquarter.text = listLaz[position].headquarter
        }
    }

    override fun getItemCount() = listLaz.size
}