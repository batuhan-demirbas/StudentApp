package com.batuhandemirbas.studentapp.model


data class Lesson(
    var name: String,
    var code: String,
    var credit: Int,
    var vize: Int? = null,
    var final: Int? = null
)
