package com.hexagonal.systemintegration.config

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.Priority
import com.hexagonal.appdomain.annotation.UseCase

class AnnotBeanConfigHolder {
    companion object {
        val CUSTOM_BEAN_ANNOTATION = UseCase::class
        const val BASE_PACKAGES = "com.hexagonal.appservice" // 컴마(,) 로 구분하여 복수개 패키지 경로 설정 가능
        const val BEAN_SCOPE = "singleton"

        val CUSTOM_PRIMARY_ANNOTATION = Priority::class
        val CUSTOM_QUALIFIER_ANNOTATION = Aliases::class
    }
}
