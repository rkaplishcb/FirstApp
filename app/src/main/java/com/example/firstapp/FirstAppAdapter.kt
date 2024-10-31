package com.example.firstapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_firstapp.view.*

class FirstAppAdapter(
    private val firstApps: MutableList<FirstApp>
) : RecyclerView.Adapter<FirstAppAdapter.FirstAppViewHolder>() {

    class FirstAppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstAppViewHolder {
        return FirstAppViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_firstapp,
                parent,
                false
            )
        )
    }

    fun addFirstApp(firstapp: FirstApp) {
        firstApps.add(firstapp)
        notifyItemInserted(firstApps.size - 1)
    }

    fun deleteDoneFirstApps() {
        firstApps.removeAll { firstapp ->
            firstapp.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvFirstAppTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvFirstAppTitle.paintFlags = tvFirstAppTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvFirstAppTitle.paintFlags = tvFirstAppTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: FirstAppViewHolder, position: Int) {
        val curFirstApp = firstApps[position]
        holder.itemView.cb
        holder.itemView.apply {
            tvFirstAppTitle.text = curFirstApp.title
            cbDone.isChecked = curFirstApp.isChecked
            toggleStrikeThrough(tvFirstAppTitle, curFirstApp.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvFirstAppTitle, isChecked)
                curFirstApp.isChecked = !curFirstApp.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return firstApps.size
    }
}