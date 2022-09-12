package com.batuhandemirbas.studentapp.data

import com.batuhandemirbas.studentapp.model.Lesson

class DataSource {
    fun loadLesson(): List<Lesson> {
        return listOf<Lesson>(
            Lesson("Optik Fiberli Haberleşme Sistemleri", "EEM4406", 3, 60, 70),
            Lesson("Optoelektronik", "EEM4303", 3, 50),
            Lesson("Elektronik Devreleri II", "EEM3302", 6, 53),
            Lesson("Elektrik Tesisleri", "EEM3501", 5, 60, 18)
        )
    }
}