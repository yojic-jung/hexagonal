package com.hexagonal.systemintegration.manager

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.Priority
import com.hexagonal.systemintegration.processor.BeanDefinitionModifyProcessor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.stereotype.Component

/**
 * Priority는 Primary로, Aliases는 Qualifer로 속성 변경 기준을 잡고
 * Processor에서 변경 작업을 위임하는 클래스
 */
@Qualifier("useCase")
@Component
class BeanDefinitionModifyByAnnotManager(
    @Qualifier("useCase")
    private val beanDefModifyProcessor: BeanDefinitionModifyProcessor
) : BeanDefinitionModifyDelegateManager {
    override fun <T> delegateProcess(beanClass: Class<T>, beanDefinitionBuilder: BeanDefinitionBuilder) {
        // annotation에 따른 beanDef 설정 메서드를 갖는 맵
        val beanDefModifyMapByAnnot =
            mapOf(
                Priority::class to { beanDefModifyProcessor.modifyPrimary(beanDefinitionBuilder) },
                Aliases::class to { beanDefModifyProcessor.modifyQualifier(beanClass, beanDefinitionBuilder) },
            )

        // annotation에 따른 beanDef 설정
        beanDefModifyMapByAnnot.forEach { (annot, modify) ->
            if (beanClass.isAnnotationPresent(annot.java)) modify()
        }
    }
}
