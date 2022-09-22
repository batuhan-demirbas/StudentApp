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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var auth: FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

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

        val currentStudent = auth.currentUser

        currentStudent?.let {
            // Name, email address, and profile photo Url
            val name = currentStudent.displayName
            val email = currentStudent.email

            binding.textView.text =
                "Merhaba ${email},lütfen hangi işlemi yapacağına karar ver."
        }

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

        // Çıkış yap butonuna basıldığında yapılacaklar
        binding.buttonExit.setOnClickListener {
            Firebase.auth.signOut()
            val action = DashboardFragmentDirections.actionDashboardFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}