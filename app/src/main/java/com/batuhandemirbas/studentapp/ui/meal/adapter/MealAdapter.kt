package com.batuhandemirbas.studentapp.ui.meal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.batuhandemirbas.studentapp.R
import com.batuhandemirbas.studentapp.model.Meal

class MealAdapter(
    private val mealList: List<Meal>
) : RecyclerView.Adapter<MealAdapter.MealVH>() {

    class MealVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView= itemView.findViewById(R.id.mealDateTextView)
        val main: Button = itemView.findViewById(R.id.mainMealButton)
        val side: Button = itemView.findViewById(R.id.sideMealButton)
        val soup: Button = itemView.findViewById(R.id.soupMealButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealVH(itemView)
    }

    override fun onBindViewHolder(holder: MealVH, position: Int) {
        val item = mealList[position]
        holder.date.text = item.date
        holder.main.text = "Ana Yemek: ${item.main}"
        holder.side.text = "Yan: ${item.side}"
        holder.soup.text = "Çorba: ${item.soup}"
    }

    override fun getItemCount(): Int {
        return mealList.size
    }
}