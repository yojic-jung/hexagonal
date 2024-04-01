package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.support.AutowireCandidateQualifier
import org.springframework.beans.factory.support.BeanDefinitionBuilder

class BeanDefinitionAttrBuildProcessor : BeanDefinitionAttrProcessor {
    override fun modifyPrimary(isPrimary: Boolean, beanDefinitionBuilder: BeanDefinitionBuilder) {
        beanDefinitionBuilder.setPrimary(isPrimary)
    }

    override fun addQualifier(qualifierValues: List<String>, beanDefinitionBuilder: BeanDefinitionBuilder) {
        qualifierValues.forEach { alias ->
            val qualifier = AutowireCandidateQualifier(Qualifier::class.java)
            qualifier.setAttribute("value", alias)
            beanDefinitionBuilder.beanDefinition.addQualifier(qualifier)
        }
    }
}
