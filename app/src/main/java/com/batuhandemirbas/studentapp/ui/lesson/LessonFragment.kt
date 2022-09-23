package com.batuhandemirbas.studentapp.ui.lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.batuhandemirbas.studentapp.ui.lesson.adapter.LessonAdapter
import com.batuhandemirbas.studentapp.data.DataSource
import com.batuhandemirbas.studentapp.databinding.FragmentLessonBinding

class LessonFragment : Fragment() {

    /** This property is only valid between onCreateView and onDestroyView */
    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myLessonList = DataSource().loadLesson()
        val layoutManager = LinearLayoutManager(context)
        val recyclerView = binding.recyclerViewLesson
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = LessonAdapter(myLessonList)
        recyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}