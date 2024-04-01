package com.hexagonal.systemintegration.manager

import com.hexagonal.systemintegration.config.AnnotConfigHolder
import com.hexagonal.systemintegration.processor.BeanDefinitionAttrProcessor
import org.springframework.beans.factory.support.BeanDefinitionBuilder

/**
 * Priority는 Primary로, Aliases는 Qualifer로 속성 변경 기준을 잡고
 * Processor에서 변경 작업을 위임하는 클래스
 */
class AnnotBeanDefinitionModifyManager(
    private val beanDefModifyProcessor: BeanDefinitionAttrProcessor
) : BeanDefinitionModifyManager {
    override fun <T> delegate(beanClass: Class<T>, beanDefinitionBuilder: BeanDefinitionBuilder) {
        if (beanClass.isAnnotationPresent(AnnotConfigHolder.PRIMARY.java)) {
            beanDefModifyProcessor.modifyPrimary(true, beanDefinitionBuilder)
        }else if (beanClass.isAnnotationPresent(AnnotConfigHolder.QUALIFIER.java)) {
            val aliasesAnnotation = beanClass.getAnnotation(AnnotConfigHolder.QUALIFIER.java)
            val aliases = aliasesAnnotation.value.split(",").map { it.trim() }
            beanDefModifyProcessor.addQualifier(aliases, beanDefinitionBuilder)
        }
    }

}
