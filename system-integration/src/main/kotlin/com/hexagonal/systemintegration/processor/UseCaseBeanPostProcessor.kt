package com.hexagonal.systemintegration.processor

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.Priority
import com.hexagonal.appdomain.annotation.UseCase
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.AutowireCandidateQualifier
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.stereotype.Component

/**
 * 빈 후처리기
 *
 * 커스텀 어노테이션 종류를 확인하여 빈 객체의 beanDefinition 상태 변경
 */
@Component
class UseCaseBeanPostProcessor : BeanFactoryPostProcessor {
    @Throws(BeansException::class)
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        val registry = beanFactory as BeanDefinitionRegistry

        val scanner = ClassPathScanningCandidateComponentProvider(false)
        scanner.addIncludeFilter(AnnotationTypeFilter(UseCase::class.java))

        // 패키지 경로 설정
        val basePackage = "com.hexagonal.appservice" // 적절한 패키지 경로로 변경

        val components = scanner.findCandidateComponents(basePackage)
        for (beanDefinition in components) {
            val beanClass = Class.forName(beanDefinition.beanClassName)
            val beanName = beanClass.simpleName.replaceFirstChar { it.lowercase() }

            val builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass)

            // @Priority 어노테이션이 있는 경우
            if (beanClass.isAnnotationPresent(Priority::class.java)) builder.setPrimary(true)

            // @Aliases 어노테이션 처리
            if (beanClass.isAnnotationPresent(Aliases::class.java)) {
                val aliasesAnnotation = beanClass.getAnnotation(Aliases::class.java)
                val aliases = aliasesAnnotation.value.split(",").map { it.trim() }
                if (aliases.isNotEmpty()) {
                    val beanDefinitionByBuilder = builder.beanDefinition
                    for (alias in aliases) {
                        val qualifier = AutowireCandidateQualifier(Qualifier::class.java)
                        qualifier.setAttribute("value", alias)

                        beanDefinitionByBuilder.addQualifier(qualifier)
                    }
                    registry.registerBeanDefinition(beanName, beanDefinitionByBuilder)
                }
            } else {
                registry.registerBeanDefinition(beanName, builder.beanDefinition)
            }
        }
    }
}
