package com.example.dog.controller

import com.example.dog.CheckDog
import com.example.dog.DogsinitialApplication
import com.example.dog.exception.ResourceNotFoundException
import com.example.dog.model.Dog
import com.example.dog.ourDogList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import com.example.dog.model.MessageDetail
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired

private val logger = LoggerFactory.getLogger(DogController::class.java)

@Autowired
var rt: RabbitTemplate? = null

@RestController
@RequestMapping("/dogs")
class DogController {


    // localhost:8080/dogs/dogs
    @RequestMapping(value = "/dogs")
    fun getAllDogs(): ResponseEntity<*> {
        logger.info("/dogs/dogs accessed")

        val message = MessageDetail("/dogs/dogs accessed", 7, false)
        rt?.convertAndSend(DogsinitialApplication.QUEUE_NAME_LOW, message)
        return ResponseEntity(ourDogList.dogList, HttpStatus.OK)
    }


    // localhost:8080/dogs/{id}
    @RequestMapping(value = "/{id}")
    fun getDogDetail(@PathVariable id: Long): ResponseEntity<*> {

        logger.trace("/dogs/$id accessed")

        val message = MessageDetail("/dogs/$id accessed", 7, true)
        rt?.convertAndSend(DogsinitialApplication.QUEUE_NAME_LOW, message)


        val rtnDog = ourDogList.findDog(object : CheckDog {
            override fun test(d: Dog): Boolean {
                return when (d.id) {
                    id -> true
                    else -> false
                }
            }
        })

        if (rtnDog == null) {
            throw ResourceNotFoundException("Dog with id $id not found")
        } else {
        }
        return ResponseEntity(rtnDog, HttpStatus.OK)
    }
}

// localhost:8080/dogs/breeds/{breed}
@RequestMapping(value = "/breeds/{breed}")
fun getDogBreeds(@PathVariable breed: String): ResponseEntity<*> {

    logger.trace("/dogs/breeds/$breed accessed")

    val message = MessageDetail("/dogs/breeds/$breed accessed", 7, true)
    rt?.convertAndSend(DogsinitialApplication.QUEUE_NAME_HIGH, message)

    val rtnDogs = ourDogList.findDogs(object : CheckDog {
        override fun test(d: Dog): Boolean {
            return when (d.breed) {
                breed -> true
                else -> false
            }
        }
    })

    if (rtnDogs.size == 0) {
        throw ResourceNotFoundException("No Dogs of breed $breed found.")
    }
    return ResponseEntity(rtnDogs, HttpStatus.OK)
}

@RequestMapping(value = "/dogtable")
fun displayDogTable(): ModelAndView {

    logger.trace("/dogs/dogtable accessed")

    val message = MessageDetail("/dogs/dogtable accessed", 7, false)
    rt?.convertAndSend(DogsinitialApplication.QUEUE_NAME_HIGH, message)

    var mav = ModelAndView()
    mav.viewName = "dogs"
    mav.addObject("dogList", ourDogList.dogList)

    return mav
}