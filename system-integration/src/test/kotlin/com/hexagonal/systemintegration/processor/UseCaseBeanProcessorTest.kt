package com.hexagonal.systemintegration.processor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.GenericBeanDefinition
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

// 테스트 목적 tmp 클래스(빈으로 등록될 대상 클래스)
class TmpClass

@SpringBootTest
class UseCaseBeanProcessorTest(
    @Autowired private val context: ApplicationContext
) {

    @Test
    fun `ApplicationContext 구현체 확인`() {
        assertInstanceOf(AnnotationConfigApplicationContext::class.java, context)
    }

    @Test
    fun `빈 정의를 통한 빈 생성`() {
        // given
        // TmpClass 빈으로 생성하기 위한 BeanDefinition 생성
        val autoConfigContext = context as AnnotationConfigApplicationContext
        val genericBeanDef = GenericBeanDefinition()
        genericBeanDef.setBeanClass(TmpClass::class.java)
        genericBeanDef.scope = "singleton"
        genericBeanDef.setPrimary(true)

        // 컨테스트에 beanDefinition 등록
        autoConfigContext.registerBeanDefinition("tmpClass", genericBeanDef)
        autoConfigContext.registerAlias("tmpClass", "tmp")

        // when
        // context에서 빈을 가져올 때, beanDefinition이 있는데 빈 인스턴스가 없다면 생성하여 가져온다.
        val bean = autoConfigContext.getBean("tmpClass")
        val beanDef = autoConfigContext.getBeanDefinition("tmpClass")

        // then
        assertInstanceOf(TmpClass::class.java, bean)
        assertThat(beanDef.isPrimary).isTrue()
    }
}
