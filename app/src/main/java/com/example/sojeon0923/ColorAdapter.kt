package com.example.sojeon0923

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(private val items : MutableList<Color>) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    class ColorViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val txtColorCode = view.findViewById<TextView>(R.id.txtColorCode)
        companion object{
            fun from(parent: ViewGroup) : ColorViewHolder{
                return ColorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.color_item, parent, false))
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.txtColorCode.text = items[position].hex
        holder.view.setBackgroundColor(android.graphics.Color.parseColor(items[position].hex))
        holder.view.setOnClickListener {
            holder.view.context.startActivity(Intent(holder.view.context, ColorDetailActivity::class.java)
                .putExtra("color", items[position]))
        }
    }

    fun changeData(newItems : List<Color>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged() //원래 이렇게 하면 안됨. 다 지우고 다시 다 넣고 변경한거 알려서 데이터 뿌리는 코드인데 바뀌는 부분만 캐치해서 바꿔주는게 좋다.
    }

    override fun getItemCount(): Int = items.size


}