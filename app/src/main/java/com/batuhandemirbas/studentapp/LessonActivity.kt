package com.batuhandemirbas.studentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batuhandemirbas.studentapp.adapter.LessonAdapter
import com.batuhandemirbas.studentapp.data.DataSource

class LessonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        val myLessonList = DataSource().loadLesson()
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLesson)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = LessonAdapter(this, myLessonList)
        recyclerView.setHasFixedSize(true)
    }
}