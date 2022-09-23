package com.batuhandemirbas.studentapp.ui.meal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.batuhandemirbas.studentapp.databinding.FragmentMealBinding
import com.batuhandemirbas.studentapp.model.Meal
import com.batuhandemirbas.studentapp.ui.meal.adapter.MealAdapter

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

        val layoutManager = LinearLayoutManager(context)
        val recyclerView = binding.mealRecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        val model: MealViewModel by viewModels()
        model.getMeals().observe(viewLifecycleOwner, Observer<List<Meal>>{ meals ->
            recyclerView.adapter = MealAdapter(meals)


            for (i in 0..meals.lastIndex) {
                Log.d("Firebase Firestore", "${meals[i].date} ${meals[i].main} ${meals[i].side}")
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}