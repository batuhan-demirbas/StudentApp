package com.batuhandemirbas.studentapp.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.batuhandemirbas.studentapp.databinding.FragmentMealBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MealFragment : Fragment() {

    /** This property is only valid between onCreateView and onDestroyView */
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        // Data pulls from Firebase Firestore
        db.collection("meals")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    binding.meal1.text = document.data.toString()
                }
            }
            .addOnFailureListener {
                // If read data fails, display a message to the user
                Snackbar.make(view, "Başarısız", Snackbar.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}