package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.support.BeanDefinitionBuilder

interface BeanDefinitionAttrProcessor {
    fun modifyPrimary(isPrimary: Boolean, beanDefinitionBuilder: BeanDefinitionBuilder)

    fun addQualifier(qualifierValues: List<String>, beanDefinitionBuilder: BeanDefinitionBuilder)
}
