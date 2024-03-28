package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory

interface BeanCreationProcessor {
    fun createAllBeans(beanFactory: ConfigurableListableBeanFactory)
}
