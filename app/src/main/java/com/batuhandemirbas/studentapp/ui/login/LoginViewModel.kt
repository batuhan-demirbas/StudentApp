package com.batuhandemirbas.studentapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.batuhandemirbas.studentapp.model.Student

class LoginViewModel: ViewModel() {

    private val student: MutableLiveData<Student> by lazy {
        MutableLiveData<Student>().also {
            loadStudent()
        }
    }

    fun getUsers(): LiveData<Student> {
        return student
    }

    private fun loadStudent() {
        // Do an asynchronous operation to fetch users.
    }
}