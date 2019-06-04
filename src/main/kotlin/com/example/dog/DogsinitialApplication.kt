package com.example.dog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


var ourDogList: DogList = DogList()

@SpringBootApplication
class DogsinitialApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(DogsinitialApplication::class.java, *args)
        }
    }

}