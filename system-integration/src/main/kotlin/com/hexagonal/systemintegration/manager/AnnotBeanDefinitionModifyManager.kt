package com.hexagonal.systemintegration.manager

import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.BEAN_SCOPE
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.PRIMARY
import com.hexagonal.systemintegration.config.AnnotBeanConfigHolder.Companion.QUALIFIER
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
        if (beanClass.isAnnotationPresent(PRIMARY.java)) {
            beanDefModifyProcessor.modifyPrimary(true, beanDefinitionBuilder)
        } // qualifier 설정
        else if (beanClass.isAnnotationPresent(QUALIFIER.java)) {
            val aliasesAnnotation = beanClass.getAnnotation(AnnotBeanConfigHolder.QUALIFIER.java)
            val aliases = aliasesAnnotation.value.split(",").map { it.trim() }
            beanDefModifyProcessor.addQualifier(aliases, beanDefinitionBuilder)
        }
    }
}
