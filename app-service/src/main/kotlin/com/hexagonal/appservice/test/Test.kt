package com.hexagonal.appservice.test

import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.Priority
import com.hexagonal.appdomain.annotation.UseCase

/** 테스트 목적 인터페이스 */
interface MyCustomInterface {
    fun a()
}

/** Primary와 Qualifier 테스트 목적 클래스 */
@Priority
@Aliases("default, primary")
@UseCase
class MyCustomDefaultClass : MyCustomInterface {
    override fun a() {
        println(1)
    }
}


/** Qualifier 테스트 목적 클래스 */
@Aliases("secondary")
@UseCase
class MyCustomSecondaryClass : MyCustomInterface {
    override fun a() {
        println(1)
    }
}