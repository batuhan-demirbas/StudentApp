package com.batuhandemirbas.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.data.DatabaseHelper
import com.batuhandemirbas.studentapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        arguments?.let {
            val number = DashboardFragmentArgs.fromBundle(it).number
            val db = DatabaseHelper(context)
            val student = db.readDataStudent(number, context)
            binding.textView.text =
                "Merhaba ${student.name},\nlütfen hangi işlemi yapacağına karar ver."
        } */


        // Derslerim butonuna tıklandığında yapılacaklar
        binding.buttonLesson.setOnClickListener {
            val actionToLessonFragment =
                DashboardFragmentDirections.actionDashboardFragmentToLessonFragment()
            Navigation.findNavController(it).navigate(actionToLessonFragment)
        }

        // Ortalama butonuna basıldığında yapılacaklar
        binding.buttonAverage.setOnClickListener {
            val actionToAverageFragment =
                DashboardFragmentDirections.actionDashboardFragmentToAverageFragment()
            Navigation.findNavController(it).navigate(actionToAverageFragment)
        }

        // Yemekhane butonuna basıldığında yapılacaklar
        binding.buttonMeal.setOnClickListener {
            val actionToMealFragment =
                DashboardFragmentDirections.actionDashboardFragmentToMealFragment()
            Navigation.findNavController(it).navigate(actionToMealFragment)
        }

        // Kampüsü gör butonuna basıldığında yapılacaklar
        binding.buttonMap.setOnClickListener {
            val actionToMapFragment =
                DashboardFragmentDirections.actionDashboardFragmentToMapFragment()
            Navigation.findNavController(it).navigate(actionToMapFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}