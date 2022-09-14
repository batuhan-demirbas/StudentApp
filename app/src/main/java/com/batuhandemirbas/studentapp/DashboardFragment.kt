package com.batuhandemirbas.studentapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val name = DashboardFragmentArgs.fromBundle(it).name
            binding.textView.text = "Merhaba $name, lütfen hangi işlemi\nyapacağına karar ver."
        }

        binding.buttonLesson.setOnClickListener {
            val actionToLessonFragment = DashboardFragmentDirections.actionDashboardFragmentToLessonFragment()
            Navigation.findNavController(it).navigate(actionToLessonFragment)
        }

        binding.buttonAverage.setOnClickListener {
            val actionToAverageFragment = DashboardFragmentDirections.actionDashboardFragmentToAverageFragment()
            Navigation.findNavController(it).navigate(actionToAverageFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}