package com.hexagonal.systemintegration.manager

import org.springframework.beans.factory.support.BeanDefinitionBuilder

/**
 * BeanDeifinition 변경에 적절한 processor를 찾아 변경 작업을 위임한다.
 */
interface BeanDefinitionModifyManager {
    fun <T> delegate(beanClass: Class<T>, beanDefinitionBuilder: BeanDefinitionBuilder)
}
