package com.example.dog.controller

import com.example.dog.CheckDog
import com.example.dog.exception.ResourceNotFoundException
import com.example.dog.model.Dog
import com.example.dog.ourDogList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

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
                 return when(d.id){
                     id -> true
                     else -> false
                 }
            }
        })
        return ResponseEntity(rtnDog, HttpStatus.OK)
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    fun getDogBreeds(@PathVariable breed: String): ResponseEntity<*> {
        val rtnDogs = ourDogList.findDogs(object: CheckDog {
            override fun test(d: Dog): Boolean {
                return when(d.breed){
                    breed -> true
                    else -> false
                }
            }
        })

        if(rtnDogs.size == 0){
            throw ResourceNotFoundException("No Dogs of breed $breed found.")
        }
        return ResponseEntity(rtnDogs, HttpStatus.OK)
    }

    @GetMapping(value = "/dogtable")
    fun displayDogTable():ModelAndView{
        var mav = ModelAndView()
        mav.viewName = "dogs"
        mav.addObject("dogList", ourDogList.dogList)

        return mav
    }
}