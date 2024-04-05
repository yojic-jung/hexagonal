package com.hexagonal.systemintegration.config

import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.CUSTOM_BEAN_ANNOTATION
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.BASE_PACKAGES
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.CUSTOM_QUALIFIER_ANNOTATION
import com.hexagonal.systemintegration.processor.BeanDefinitionRegisterProcessor
import com.hexagonal.systemintegration.processor.QualifierTypeProcessor
import com.hexagonal.systemintegration.util.AnnotProcessorFactory
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.stereotype.Component

/**
 * Def : 사용자가 정의한 어노테이션이 붙은 클래스의 beanDefinition 등록 Processor
 * Desc : 사용자가 정의한
 */
@Component
class AnnotBeanFactoryPostProcessor(
    private val beanDefRegistrar: BeanDefinitionRegisterProcessor = AnnotProcessorFactory.getBeanDefinitionRegistrar(),
    private val qualifierTypeProcessor: QualifierTypeProcessor = AnnotProcessorFactory.getQualifierTypeProcessor(),
) : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        // 커스텀 어노테이션 붙은 클래스 beanDefinition으로 생성
        val registry = beanFactory as BeanDefinitionRegistry
        beanDefRegistrar.registerOnlyWith(CUSTOM_BEAN_ANNOTATION, BASE_PACKAGES, registry)

        // 의존(참조속성)관계 설정에 @Qualifier처럼 사용될 어노테이션 타입 지정
        qualifierTypeProcessor.add(CUSTOM_QUALIFIER_ANNOTATION, beanFactory as DefaultListableBeanFactory)
    }
}
