package com.hexagonal.systemintegration.processor

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.Priority
import com.hexagonal.appdomain.annotation.UseCase
import com.hexagonal.appservice.test.MyCustomInterface
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.AnnotationConfigApplicationContext


@SpringBootTest
class UsecasePostProcessorTest(
    @Autowired private val myCustomInterface: MyCustomInterface,
) {

    @Test
    fun `primary 속성 등록 테스트`() {
    }

}