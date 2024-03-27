package com.hexagonal.systemintegration.processor

import com.hexagonal.appservice.test.MyCustomInterface
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootTest
class UsecasePostProcessorTest(
    @Autowired @Qualifier("secondary") private val myCustomInterface: MyCustomInterface,
) {
    @Autowired
    lateinit var applicationContext: AnnotationConfigApplicationContext

    @Test
    fun printAllBeanNames() {
        myCustomInterface.a()
        val beanNames = applicationContext.beanDefinitionNames
        println("All Bean Names:")
        beanNames.forEach { beanName ->
            val simpleBeanName = beanName.substringAfterLast(".") // 패키지명을 제외한 빈의 이름만 추출
            println(simpleBeanName)
        }
    }

    @Test
    fun checkQualifier() {
        val beanName = "myCustomSecondaryClass"
        val beanDefinition = applicationContext.getBeanDefinition(beanName)
        val qualifiers =
            beanDefinition.attributeNames()
                .filter { it.startsWith("qualifier.") }
                .map { beanDefinition.getAttribute(it).toString() }

        println("Qualifiers for bean '$beanName': $qualifiers")
    }

    @Test
    fun `primary 속성 등록 테스트`() {
    }
}
