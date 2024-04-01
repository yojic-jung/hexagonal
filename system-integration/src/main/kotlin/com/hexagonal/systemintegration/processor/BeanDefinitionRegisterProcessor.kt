package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory

interface BeanDefinitionRegisterProcessor {
    fun createAllBeans(beanFactory: ConfigurableListableBeanFactory)
}
