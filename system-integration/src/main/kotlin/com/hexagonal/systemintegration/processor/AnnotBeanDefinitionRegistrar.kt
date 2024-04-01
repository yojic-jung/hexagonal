package com.hexagonal.systemintegration.processor

import com.hexagonal.systemintegration.manager.BeanDefinitionModifyManager
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import kotlin.reflect.KClass

/**
 * 빈팩토리 후처리기
 *
 * basePackage를 기반으로 @UseCase 어노테이션이 붙은 클래스를 스프링 빈으로 등록함
 * @Priority가 붙은 경우 primary 속성을 부여함
 * @Aliases가 붙은 경우 value값을 읽어와 qualifier 속성을 부여함
 *
 * 참고.
 * 빈팩토리 생성 이후 실행되는 메서드로 beanDefinition 변경 가능(빈 생성 가능)
 * 스프링 부트 autoConfig의 ApplicationContext 구현체인 AnnotationConfigApplicationContext은
 * beanDefinition이 정의 되어있다면 해당 정의를 기반으로 빈을 인스턴스화함
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanFactoryPostProcessor.html
 *
 */
class AnnotBeanDefinitionRegistrar(
    private val beanDefModifyDelegateManager: BeanDefinitionModifyManager,
) : BeanDefinitionRegisterProcessor {
    companion object {
        private const val USECASE_BASE_PACAKGE = "com.hexagonal.appservice"
    }

    @Throws(BeansException::class)
    override fun registerFromType(targetAnnot: KClass<out Annotation>, beanFactory: ConfigurableListableBeanFactory) {
        // beanDef 등록 레지스트리(beanDef가 등록되면 자동으로 인스턴스화 해준다.)
        val registry = beanFactory as BeanDefinitionRegistry

        // UseCase annotation filter 등록
        val componentScanner = ClassPathScanningCandidateComponentProvider(false)
        val useCaseAnnotFilter = AnnotationTypeFilter(targetAnnot.java)
        componentScanner.addIncludeFilter(useCaseAnnotFilter)

        // basePackage에 대하여 빈 후보군 beanDefinition 추출
        val beanDefOfCandidates = componentScanner.findCandidateComponents(USECASE_BASE_PACAKGE)

        // 빈 후보들에 대하여 빈 정의 설정 및 등록
        for (beanDef in beanDefOfCandidates) {
            val beanClass = Class.forName(beanDef.beanClassName)
            val beanName = beanClass.simpleName.replaceFirstChar { it.lowercase() }
            val beanDefBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass)

            // beanDefModifyDelegateManager를 통해 useCase 클래스의 빈 정의 변경
            beanDefModifyDelegateManager.delegate(beanClass, beanDefBuilder)

            // 빈 등록
            registry.registerBeanDefinition(beanName, beanDefBuilder.beanDefinition)
        }
    }
}
