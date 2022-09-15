package com.batuhandemirbas.studentapp.data

import com.batuhandemirbas.studentapp.model.Student

class StudentSource {
    fun loadStudents(): List<Student> {
        return listOf<Student> (
            Student(31811626, "Batuhan Demirbaş", 12345),
            Student(31811627, "Mehmet Ertürk", 123456),
            Student(31811628, "Ali Ak", 1234567),
            Student(31811629, "Fatih Erdemir", 12345678),
        )
    }
}