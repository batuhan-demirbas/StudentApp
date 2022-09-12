package com.batuhandemirbas.studentapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.batuhandemirbas.studentapp.R
import com.batuhandemirbas.studentapp.model.Lesson

class LessonAdapter(
    private val context: Context,
    private val lessonList: List<Lesson>) : RecyclerView.Adapter<LessonAdapter.LessonVH>() {

    class LessonVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewLesson)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_lesson, parent, false)
        return LessonVH(itemView)
    }

    override fun onBindViewHolder(holder: LessonVH, position: Int) {
        val item = lessonList.get(position)
        holder.textView.text = "${item.code}, ${item.name}, ${item.credit}, ${item.vize}, ${item.final}"
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }
}