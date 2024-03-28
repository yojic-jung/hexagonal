package com.hexagonal.systemintegration.processor

import com.hexagonal.appdomain.annotation.Aliases
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.support.AutowireCandidateQualifier
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.stereotype.Component

@Qualifier("useCase")
@Component
class UseCaseBeanDefinitionModifyProcessor : BeanDefinitionModifyProcessor {
    override fun modifyPrimary(beanDefinitionBuilder: BeanDefinitionBuilder) {
        beanDefinitionBuilder.setPrimary(true)
    }

    override fun <T> modifyQualifier(
        beanClass: Class<T>,
        beanDefinitionBuilder: BeanDefinitionBuilder,
    ) {
        val aliasesAnnotation = beanClass.getAnnotation(Aliases::class.java)
        val aliases = aliasesAnnotation.value.split(",").map { it.trim() }
        aliases.forEach { alias ->
            val qualifier = AutowireCandidateQualifier(Qualifier::class.java)
            qualifier.setAttribute("value", alias)

            beanDefinitionBuilder.beanDefinition.addQualifier(qualifier)
        }
    }
}
