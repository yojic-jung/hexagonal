package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.support.BeanDefinitionBuilder

interface BeanDefinitionModifyProcessor {
    fun modifyPrimary(beanDefinitionBuilder: BeanDefinitionBuilder)

    fun <T> modifyQualifier(beanClass: Class<T>, beanDefinitionBuilder: BeanDefinitionBuilder)
}
