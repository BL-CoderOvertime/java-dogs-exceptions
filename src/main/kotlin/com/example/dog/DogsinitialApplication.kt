package com.example.dog

import org.springframework.beans.factory.getBean
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc


var ourDogList: DogList = DogList()

@EnableWebMvc
@SpringBootApplication
class DogsinitialApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var ctx: ApplicationContext = SpringApplication.run(DogsinitialApplication::class.java, *args)
            var dispatcherServlet: DispatcherServlet = ctx.getBean("dispatcherServlet") as DispatcherServlet
            dispatcherServlet.setThrowExceptionIfNoHandlerFound(true)
        }
    }

}