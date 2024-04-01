package com.hexagonal.systemintegration.config

import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.BEAN
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.QUALIFIER
import com.hexagonal.systemintegration.processor.BeanDefinitionRegisterProcessor
import com.hexagonal.systemintegration.processor.QualifierTypeProcessor
import com.hexagonal.systemintegration.util.AnnotProcessorFactory
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.stereotype.Component

/**
 * 프로젝트 수준 BeanFactoryPostProcessor
 */
@Component
class AnnotBeanFactoryPostProcessor(
    private val beanDefRegistrar: BeanDefinitionRegisterProcessor = AnnotProcessorFactory.getBeanDefinitionRegistrar(),
    private val qualifierProcessor: QualifierTypeProcessor = AnnotProcessorFactory.getQualifierTypeProcessor(),
) : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        // useCase 어노테이션 붙은 클래스 beanDefinition으로 생성
        beanDefRegistrar.registerFromType(BEAN, beanFactory)
        // qualfier 타입에 Aliases도 추가(의존 주입 가능)
        qualifierProcessor.addQualifierType(QUALIFIER, beanFactory as DefaultListableBeanFactory)
    }
}
