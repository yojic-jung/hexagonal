package com.hexagonal.systemintegration.util

import com.hexagonal.systemintegration.manager.AnnotBeanDefinitionModifyManager
import com.hexagonal.systemintegration.processor.*

class AnnotProcessorFactory {
    companion object {
        fun getBeanDefinitionRegistrar() : BeanDefinitionRegisterProcessor {
            val beanDefinitionModifyProcessor = BeanDefinitionAttrModifier()
            val beanDefinitionModifyByAnnotManager = AnnotBeanDefinitionModifyManager(beanDefinitionModifyProcessor)
            return AnnotBeanDefinitionRegistrar(beanDefinitionModifyByAnnotManager)
        }

        fun getQualifierTypeProcessor() : QualifierTypeProcessor {
            return AutowireQualifierTypeResolver()
        }
    }

}
