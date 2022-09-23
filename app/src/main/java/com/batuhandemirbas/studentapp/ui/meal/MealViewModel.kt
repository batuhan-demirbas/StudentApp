package com.batuhandemirbas.studentapp.ui.meal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.batuhandemirbas.studentapp.model.Meal
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MealViewModel: ViewModel() {
    val meals: MutableLiveData<List<Meal>> by lazy {
        MutableLiveData<List<Meal>>().also {
            loadMeals()
        }
    }

    fun getMeals(): LiveData<List<Meal>> {
        return meals
    }

    private fun loadMeals() {
        // Do an asynchronous operation to fetch users.
        val db = Firebase.firestore

        // Data pulls from Firebase Firestore
        db.collection("meals")
            .get()
            .addOnSuccessListener { result ->
                val dbMeals = mutableListOf<Meal>()

                for (document in result) {
                    val main = document.data["main"].toString()
                    val side = document.data["side"].toString()
                    val soup = document.data["soup"].toString()
                    val snack = document.data["snack"].toString()
                    dbMeals.add(Meal(document.id, main, side, soup, snack))
                }
                meals.value = dbMeals
            }
            .addOnFailureListener {
                // If read data fails, display a message to the logcat
                Log.d("Firebase Firestore", "Yemek verileri alınamadı")
            }
    }
}