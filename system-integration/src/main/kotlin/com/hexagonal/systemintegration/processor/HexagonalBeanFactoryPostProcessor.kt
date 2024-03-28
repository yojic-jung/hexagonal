package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

/**
 * 프로젝트 수준 BeanFactoryPostProcessor
 */
@Component
class HexagonalBeanFactoryPostProcessor(
    @Qualifier("useCase") private val beanCreationProcessor: BeanCreationProcessor,
) : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        // useCase 어노테이션 붙은 클래스 빈으로 생성
        beanCreationProcessor.createAllBeans(beanFactory)
    }
}
