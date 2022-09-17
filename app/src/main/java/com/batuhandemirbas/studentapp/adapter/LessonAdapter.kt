package com.batuhandemirbas.studentapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.batuhandemirbas.studentapp.R
import com.batuhandemirbas.studentapp.model.Lesson

class LessonAdapter(
    private val lessonList: List<Lesson>
) : RecyclerView.Adapter<LessonAdapter.LessonVH>() {

    class LessonVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lessonName: TextView = itemView.findViewById(R.id.lessonName)
        val lessonCode: TextView = itemView.findViewById(R.id.lessonCode)
        val lessonVize: Button = itemView.findViewById(R.id.lessonVize)
        val lessonFinal: Button = itemView.findViewById(R.id.lessonFinal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_lesson, parent, false)
        return LessonVH(itemView)
    }

    override fun onBindViewHolder(holder: LessonVH, position: Int) {
        val item = lessonList.get(position)
        holder.lessonName.text = item.name
        holder.lessonCode.text = item.code
        holder.lessonVize.text = "Vize: ${item.vize.toString()}"
        holder.lessonFinal.text = "Final: ${item.final.toString()}"
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }
}