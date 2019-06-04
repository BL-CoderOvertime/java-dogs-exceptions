package com.example.dog.model

import com.example.dog.exception.ValidationError
import java.util.HashMap

class ErrorDetail {


    var title: String? = null
    var status: Int = 0
    var detail: String? = null
    private val timestamp: String? = null
    var developerMessage: String? = null
    private val errors = HashMap<String, List<ValidationError>>()

    fun setTimestamp(time: Long) {

        //TODO set up timestamp 1:07pm

    }
}