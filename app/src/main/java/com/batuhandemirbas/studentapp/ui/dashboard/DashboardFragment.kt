package com.batuhandemirbas.studentapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
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

        //
        currentStudent?.let {
            // Name, email address, and profile photo Url
            val email = currentStudent.email

            if (email != null) {
                binding.textView.text =
                    "Merhaba ${email.split("@")[0]},lütfen hangi işlemi yapacağına karar ver."
            }
        }

        with(binding) {

            // Lesson button
            buttonLesson.setOnClickListener {
                // Navigate to lesson fragment
                val actionToLessonFragment =
                    DashboardFragmentDirections.actionDashboardFragmentToLessonFragment()
                Navigation.findNavController(it).navigate(actionToLessonFragment)
            }

            // Average button
            buttonAverage.setOnClickListener {
                // Navigate to Average fragment
                val actionToAverageFragment =
                    DashboardFragmentDirections.actionDashboardFragmentToAverageFragment()
                Navigation.findNavController(it).navigate(actionToAverageFragment)
            }

            // Meal button
            buttonMeal.setOnClickListener {
                // Navigate to meal fragment
                val actionToMealFragment =
                    DashboardFragmentDirections.actionDashboardFragmentToMealFragment()
                Navigation.findNavController(it).navigate(actionToMealFragment)
            }

            // Map button
            buttonMap.setOnClickListener {
                // Navigate to map fragment
                val actionToMapFragment =
                    DashboardFragmentDirections.actionDashboardFragmentToMapFragment()
                Navigation.findNavController(it).navigate(actionToMapFragment)
            }

            // Sign out button
            buttonSignOut.setOnClickListener {
                // Current user sign out
                Firebase.auth.signOut()

                // Navigate to login fragment
                val actionToLoginFragment = DashboardFragmentDirections.actionDashboardFragmentToLoginFragment()
                Navigation.findNavController(it).navigate(actionToLoginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}