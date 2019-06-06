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

            val logger = LoggerFactory.getLogger(DogsinitialApplication::class.java)
            logger.info("Testing 123")
        }

        const val EXCHANGE_NAME = "LambdaServer"
        const val QUEUE_NAME_LOW = "LowPriorityQueue"
        const val QUEUE_NAME_HIGH = "HighPriorityQueue"
    }

    @Bean
    fun appExchange(): TopicExchange {
        return TopicExchange(EXCHANGE_NAME)
    }

    @Bean
    fun appQueueHigh(): Queue {
        return Queue(QUEUE_NAME_HIGH)
    }

    @Bean
    fun declareBindingHigh(): Binding {
        return BindingBuilder.bind(appQueueHigh()).to(appExchange()).with(QUEUE_NAME_HIGH)
    }

    @Bean
    fun appQueueLow(): Queue {
        return Queue(QUEUE_NAME_LOW)
    }

    @Bean
    fun declareBindingLow(): Binding {
        return BindingBuilder.bind(appQueueLow()).to(appExchange()).with(QUEUE_NAME_LOW)
    }

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }
}