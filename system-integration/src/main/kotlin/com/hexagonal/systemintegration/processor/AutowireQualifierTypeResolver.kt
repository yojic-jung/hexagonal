package com.hexagonal.systemintegration.processor

import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.CUSTOM_QUALIFIER_ANNOTATION
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import kotlin.reflect.KClass

class AutowireQualifierTypeResolver : QualifierTypeProcessor {
    // 의존주입시 aliases도 qualifier처럼 사용될 수 있게 Qualifier 타입에 Aliases 타입 추가
    override fun add(annotation: KClass<out Annotation>, beanFactory: DefaultListableBeanFactory) {
        val qualifierResolver = beanFactory.autowireCandidateResolver
        if (qualifierResolver is QualifierAnnotationAutowireCandidateResolver) {
            qualifierResolver.addQualifierType(CUSTOM_QUALIFIER_ANNOTATION.java)
        }
    }
}
