package com.hexagonal.systemintegration.processor

import org.springframework.beans.factory.support.BeanDefinitionRegistry
import kotlin.reflect.KClass

/**
 * 빈팩토리 후처리기
 *
 * basePackage를 기반으로 @UseCase 어노테이션이 붙은 클래스를 스프링 빈으로 등록함
 *
 * 참고.
 * 빈팩토리 생성 이후 실행되는 메서드로 beanDefinition 변경 가능(빈 생성 가능)
 * 스프링 부트 autoConfig의 ApplicationContext 구현체인 AnnotationConfigApplicationContext은
 * beanDefinition이 정의 되어있다면 해당 정의를 기반으로 빈을 인스턴스화함
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanFactoryPostProcessor.html
 */
interface BeanDefinitionRegisterProcessor {
    fun registerOnlyWith(
        customBeanAnnotation: KClass<out Annotation>,
        basePackages: String,
        registry: BeanDefinitionRegistry,
    )
}
