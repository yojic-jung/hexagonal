package com.hexagonal.systemintegration.config

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.Priority
import com.hexagonal.appdomain.annotation.UseCase

class AnnotBeanConfigHolder {
    companion object {
        const val BEAN_BASE_PACKAGE = "com.hexagonal.appservice"
        const val BEAN_SCOPE = "singleton"
        val BEAN = UseCase::class
        val PRIMARY = Priority::class
        val QUALIFIER = Aliases::class
    }
}
