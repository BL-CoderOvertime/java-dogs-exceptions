package com.example.dog

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.ArrayList

@RestController
@RequestMapping("/dogs")
class DogController {
    // localhost:8080/dogs/dogs
    val allDogs: ResponseEntity<*>
        @GetMapping(value = "/dogs")
        get() = ResponseEntity(ourDogList.dogList, HttpStatus.OK)

    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}")
    fun getDogDetail(@PathVariable id: Long): ResponseEntity<*> {
        val rtnDog = ourDogList.findDog(object: CheckDog {
            override fun test(d: Dog): Boolean {
                 return test(d)
            }
        })
        return ResponseEntity(rtnDog, HttpStatus.OK)
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    fun getDogBreeds(@PathVariable breed: String): ResponseEntity<*> {
        val rtnDogs = ourDogList.findDogs(object: CheckDog{
            override fun test(d: Dog): Boolean {
                return test(d)
            }
        })
        return ResponseEntity(rtnDogs, HttpStatus.OK)
    }
}