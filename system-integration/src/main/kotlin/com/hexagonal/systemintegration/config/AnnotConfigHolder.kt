package com.hexagonal.systemintegration.config

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.Priority
import com.hexagonal.appdomain.annotation.UseCase

class AnnotConfigHolder {
    companion object {
        val BEAN = UseCase::class
        val PRIMARY = Priority::class
        val QUALIFIER = Aliases::class
    }
}
