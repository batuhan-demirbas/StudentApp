package com.batuhandemirbas.studentapp

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.batuhandemirbas.studentapp.data.DatabaseHelper
import com.batuhandemirbas.studentapp.data.MealContract
import com.batuhandemirbas.studentapp.databinding.FragmentMealBinding
import com.batuhandemirbas.studentapp.model.Meal

class MealFragment : Fragment() {

    private var _binding: FragmentMealBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
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

        val db = DatabaseHelper(context)

        // db.writeMealData(context)

        val meals: MutableList<Meal> = db.readDataMeals(context)

        val meal1 = meals[0]
        val meal2 = meals[1]
        binding.meal1.text = "${meal1.date} ${meal1.main} ${meal1.snack} "
        binding.meal2.text = "${meal2.date} ${meal2.main} ${meal2.snack} "
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}