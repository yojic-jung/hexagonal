package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import kotlin.reflect.KClass

interface BeanDefinitionRegisterProcessor {
    fun registerFromType(annotation: KClass<out Annotation>, beanFactory: ConfigurableListableBeanFactory)
}
