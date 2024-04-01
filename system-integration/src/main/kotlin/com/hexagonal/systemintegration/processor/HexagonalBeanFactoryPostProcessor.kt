package com.hexagonal.systemintegration.processor

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.systemintegration.manager.BeanDefinitionModifyByAnnotManager
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.stereotype.Component

/**
 * 프로젝트 수준 BeanFactoryPostProcessor
 */
@Component
class HexagonalBeanFactoryPostProcessor : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        // todo 빈팩토리 구현
        val beanDefinitionModifyProcessor = UseCaseBeanDefinitionModifyProcessor()
        val beanDefinitionModifyByAnnotManager = BeanDefinitionModifyByAnnotManager(beanDefinitionModifyProcessor)
        val beanCreationProcessor = UseCaseBeanDefinitionRegisterProcessor(beanDefinitionModifyByAnnotManager)

        // useCase 어노테이션 붙은 클래스 빈으로 생성
        beanCreationProcessor.createAllBeans(beanFactory)

        // todo 기능 밖으로 빼기
        // 의존주입시 aliases도 qualifier처럼 사용될 수 있게 Qualifier 타입에 Aliases 타입 추가
        if(beanFactory is DefaultListableBeanFactory) {
            val qualifierResolver = beanFactory.autowireCandidateResolver
            if(qualifierResolver is QualifierAnnotationAutowireCandidateResolver) {
                qualifierResolver.addQualifierType(Aliases::class.java)
            }
        }
    }
}
