package com.hexagonal.systemintegration.config

import com.hexagonal.systemintegration.manager.BeanDefinitionModifyByAnnotManager
import com.hexagonal.systemintegration.processor.UseCaseBeanDefinitionRegisterProcessor
import com.hexagonal.systemintegration.processor.UseCaseBeanDefinitionModifyProcessor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCasesConfig {
    @Qualifier("useCase")
    @Bean
    fun useCaseBeanDefinitionModifyProcessor() = UseCaseBeanDefinitionModifyProcessor()

    @Qualifier("useCase")
    @Bean
    fun beanDefinitionModifyByAnnotManager() =
        BeanDefinitionModifyByAnnotManager(useCaseBeanDefinitionModifyProcessor())

    @Qualifier("useCase")
    @Bean
    fun useCaseBeanCreationProcessor() = UseCaseBeanDefinitionRegisterProcessor(beanDefinitionModifyByAnnotManager())

}
