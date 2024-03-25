package com.hexagonal.systemintegration.processor

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.GenericBeanDefinition
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootTest
class UseCaseBeanProcessorTest(
    @Autowired private val context: ApplicationContext
) {

    @Test
    fun `ApplicationContext 구현체 확인`() {
        assertInstanceOf(AnnotationConfigApplicationContext::class.java, context)
    }

    @Test
    fun `빈 저장`() {
        // given
        val autoConfigContext = context as AnnotationConfigApplicationContext
        val genericBeanDef = GenericBeanDefinition()
        genericBeanDef.setBeanClass(TmpClass::class.java)
        genericBeanDef.scope = "singleton"
        genericBeanDef.setPrimary(true)

        autoConfigContext.registerBeanDefinition("tmpClass", genericBeanDef)
        autoConfigContext.registerAlias("tmpClass", "tmp")

        // when
        val bean = autoConfigContext.getBean("tmpClass")
        val beanDef = autoConfigContext.getBeanDefinition("tmpClass")

        // then
        println(bean)
        println(beanDef.isPrimary)
    }
}

class TmpClass {
    fun tmp() = 1
}