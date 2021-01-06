package com.arc.secureapikotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FanAdapter(var androidList: List<ModelFan>) :
    RecyclerView.Adapter<ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ItemHolder, i: Int) {
        viewHolder.tv_name.setText(androidList[i].name)
        viewHolder.tv_version.setText(androidList[i].ver)
        viewHolder.tv_api_level.setText(androidList[i].api)
    }

    override fun getItemCount(): Int {
        return androidList.size
    }
}