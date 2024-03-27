//package com.hexagonal.systemintegration.processor
//
//import com.hexagonal.appdomain.annotation.Aliases
//import com.hexagonal.appdomain.annotation.Priority
//import org.springframework.beans.BeansException
//import org.springframework.beans.factory.config.BeanPostProcessor
//import org.springframework.context.annotation.AnnotationConfigApplicationContext
//import org.springframework.stereotype.Component
//
///**
// * 빈 후처리기
// *
// * 커스텀 어노테이션 종류를 확인하여 빈 객체의 beanDefinition 상태 변경
// */
//@Component
//class UsecasePostProcessor(
//    private val context: AnnotationConfigApplicationContext,
//) : BeanPostProcessor {
//
//    // 빈 객체가 사용 가능한 초기화가 이루어진 이후 빈 정의 상태 변경 작업 실행
//    @Throws(BeansException::class)
//    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
//        // annotation 종류에 따라 beanDefinition 상태 변경 함수를 갖는 맵
//        val beanDefRegisterMap = mapOf(
//            Priority::class.java to {
//                val beanDef = context.getBeanDefinition(beanName)
//                beanDef.isPrimary = true
//            },
//            Aliases::class.java to {
//                val annotation = bean::class.java.getAnnotation(Aliases::class.java)
//                val qualifiers = annotation.value
//                context.registerAlias(beanName, qualifiers)
//            },
//        )
//
//
//        // 빈 정의 변경
//        updateBeanDef(bean, beanName, beanDefRegisterMap)
//
//        return bean
//    }
//
//    // 빈 객체에 붙어 있는 어노테이션 확인 후 beanDefinition 변경 메서드 실행
//    private fun updateBeanDef(bean: Any, beanName: String, beanDefRegisterMap: Map<Class<out Annotation>, () -> Unit>) {
//        beanDefRegisterMap.forEach {
//            if (bean::class.java.isAnnotationPresent(it.key)) { // 어노테이션이 붙어 있으면
//                it.value() // 함수 실행
//                println(beanName)
//            }
//        }
//    }
//}