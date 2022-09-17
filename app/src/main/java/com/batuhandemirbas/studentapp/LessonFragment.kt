package com.batuhandemirbas.studentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.batuhandemirbas.studentapp.adapter.LessonAdapter
import com.batuhandemirbas.studentapp.data.DataSource
import com.batuhandemirbas.studentapp.databinding.FragmentLessonBinding

class LessonFragment : Fragment() {
    private var _binding: FragmentLessonBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        val view = binding.root

        val myLessonList = DataSource().loadLesson()
        val layoutManager = LinearLayoutManager(context)
        val recyclerView = binding.recyclerViewLesson
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = LessonAdapter(myLessonList)
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}