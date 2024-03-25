package com.hexagonal.systemintegration.config

import com.hexagonal.appdomain.annotation.UseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.BeanDefinitionHolder
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootTest
class UseCaseConfigTest(
    @Autowired private val context: ApplicationContext
) {

    @Test
    fun `app-service 모듈 usecase 빈 등록`(){
        // given & when
        val useCaseBeans = context.getBeansWithAnnotation(UseCase::class.java)

        // then
        assertThat(useCaseBeans.size).isGreaterThan(0)
    }

}