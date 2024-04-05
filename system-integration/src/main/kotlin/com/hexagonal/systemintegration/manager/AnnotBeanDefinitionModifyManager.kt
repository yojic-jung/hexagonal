package com.hexagonal.systemintegration.manager

import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.BEAN_SCOPE
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.CUSTOM_PRIMARY_ANNOTATION
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.CUSTOM_QUALIFIER_ANNOTATION
import com.hexagonal.systemintegration.processor.BeanDefinitionAttrProcessor
import org.springframework.beans.factory.support.BeanDefinitionBuilder

/**
 * Priority는 Primary로, Aliases는 Qualifer로 속성 변경 기준을 잡고
 * Processor에서 변경 작업을 위임하는 클래스
 */
class AnnotBeanDefinitionModifyManager(
    private val beanDefModifyProcessor: BeanDefinitionAttrProcessor,
) : BeanDefinitionModifyManager {
    override fun <T> delegate(beanClass: Class<T>, beanDefinitionBuilder: BeanDefinitionBuilder) {
        // scope 설정
        beanDefinitionBuilder.setScope(BEAN_SCOPE)

        // primary 설정
        if (beanClass.isAnnotationPresent(CUSTOM_PRIMARY_ANNOTATION.java)) {
            beanDefModifyProcessor.modifyPrimary(true, beanDefinitionBuilder)
        } // qualifier 설정
        else if (beanClass.isAnnotationPresent(CUSTOM_QUALIFIER_ANNOTATION.java)) {
            val aliasesAnnotation = beanClass.getAnnotation(CUSTOM_QUALIFIER_ANNOTATION.java)
            val aliases = aliasesAnnotation.value.split(",").map { it.trim() }
            beanDefModifyProcessor.addQualifier(aliases, beanDefinitionBuilder)
        }
    }
}
