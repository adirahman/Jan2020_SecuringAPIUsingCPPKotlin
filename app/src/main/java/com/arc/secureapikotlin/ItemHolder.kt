package com.arc.secureapikotlin

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tv_name: TextView
    var tv_version: TextView
    var tv_api_level: TextView

    init {
        tv_name = view.findViewById(R.id.tv_name)
        tv_version = view.findViewById(R.id.tv_version)
        tv_api_level = view.findViewById(R.id.tv_api_level)
    }
}