package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.support.DefaultListableBeanFactory
import kotlin.reflect.KClass

interface QualifierTypeProcessor {
    fun addQualifierType(annotation: KClass<out Annotation>, beanFactory: DefaultListableBeanFactory)
}
