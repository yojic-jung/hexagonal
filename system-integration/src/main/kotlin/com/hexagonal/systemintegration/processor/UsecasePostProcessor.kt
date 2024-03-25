package com.hexagonal.systemintegration.processor

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.Priority
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.GenericBeanDefinition
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class UsecasePostProcessor(
    private val context: AnnotationConfigApplicationContext,
    private val beanDefRegistry: BeanDefinitionRegistry
) : BeanPostProcessor {

    @Throws(BeansException::class)
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        // Primary 설정
        if (bean::class.java.isAnnotationPresent(Priority::class.java)) {
            val beanDef = beanDefRegistry.getBeanDefinition(beanName)
            beanDef.isPrimary = true
            context.refresh()
        }

        // Qualifier 설정
        if (bean::class.java.isAnnotationPresent(Aliases::class.java)) {
            // todo 쉼표로 들어갔을때 여러개 등록 해주는지 테스트 필요
            val qualifiers = bean::class.java.getAnnotation(Aliases::class.java).value
            context.registerAlias(beanName, qualifiers)
            context.refresh()
        }

        return bean
    }

}