package com.hexagonal.appdomain.annotation

import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target

@Target(ElementType.TYPE, ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
annotation class Priority