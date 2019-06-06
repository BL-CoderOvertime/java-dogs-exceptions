package com.example.dog.model

import java.util.concurrent.atomic.AtomicLong

class Dog {
    var id: Long = 0
        private set
    var breed: String? = null
    var weight: Int = 0
    var isApartmentSuitable: Boolean = false

    constructor(breed: String, weight: Int, apartmentSuitable: Boolean) {
        this.id = counter.incrementAndGet()
        this.breed = breed
        this.weight = weight
        this.isApartmentSuitable = apartmentSuitable
    }

    constructor(toClone: Dog) {
        this.id = toClone.id
        this.breed = toClone.breed
        this.weight = toClone.weight
        this.isApartmentSuitable = toClone.isApartmentSuitable
    }



    companion object {
        private val counter = AtomicLong()
    }
}