package com.hexagonal.appdomain.annotation

import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target


@Target(ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
annotation class Aliases(val value: String = "")
