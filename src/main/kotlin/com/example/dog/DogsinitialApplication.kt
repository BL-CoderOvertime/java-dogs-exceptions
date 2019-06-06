package com.example.dog

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder




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

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }
}