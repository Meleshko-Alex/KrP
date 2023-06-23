package com.example.krp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.krp.R
import com.example.krp.data.room.SheetRow

class CustomRecyclerAdapter(private val data: List<SheetRow>, private val titles: SheetRow) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val field1: TextView = itemView.findViewById(R.id.tv_field_1)
        val field2: TextView = itemView.findViewById(R.id.tv_field_2)
        val field3: TextView = itemView.findViewById(R.id.tv_field_3)
        val field4: TextView = itemView.findViewById(R.id.tv_field_4)
        val field5: TextView = itemView.findViewById(R.id.tv_field_5)
        val field6: TextView = itemView.findViewById(R.id.tv_field_6)
        val field7: TextView = itemView.findViewById(R.id.tv_field_7)
        val field8: TextView = itemView.findViewById(R.id.tv_field_8)
        val field9: TextView = itemView.findViewById(R.id.tv_field_9)
        val field10: TextView = itemView.findViewById(R.id.tv_field_10)
        val field11: TextView = itemView.findViewById(R.id.tv_field_11)
        val field12: TextView = itemView.findViewById(R.id.tv_field_12)
        val field13: TextView = itemView.findViewById(R.id.tv_field_13)
        val field14: TextView = itemView.findViewById(R.id.tv_field_14)
        val field15: TextView = itemView.findViewById(R.id.tv_field_15)
        val field16: TextView = itemView.findViewById(R.id.tv_field_16)
        val field17: TextView = itemView.findViewById(R.id.tv_field_17)
        val field18: TextView = itemView.findViewById(R.id.tv_field_18)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.field1.text = titles.column1 + ": " + data[position].column1
        holder.field2.text = titles.column2 + ": " + data[position].column2
        holder.field3.text = titles.column3 + ": " + data[position].column3
        holder.field4.text = titles.column4 + ": " + data[position].column4
        holder.field5.text = titles.column5 + ": " + data[position].column5
        holder.field6.text = titles.column6 + ": " + data[position].column6
        holder.field7.text = titles.column7 + ": " + data[position].column7
        holder.field8.text = titles.column8 + ": " + data[position].column8
        holder.field9.text = titles.column9 + ": " + data[position].column9
        holder.field10.text = titles.column10 + ": " + data[position].column10
        holder.field11.text = titles.column11 + ": " + data[position].column11
        holder.field12.text = titles.column12 + ": " + data[position].column12
        holder.field13.text = titles.column13 + ": " + data[position].column13
        holder.field14.text = titles.column14 + ": " + data[position].column14
        holder.field15.text = titles.column15 + ": " + data[position].column15
        holder.field16.text = titles.column16 + ": " + data[position].column16
        holder.field17.text = titles.column17 + ": " + data[position].column17
        holder.field18.text = titles.column18 + ": " + data[position].column18
    }

    override fun getItemCount(): Int {
        return data.size
    }
}