package com.hexagonal.restapi.member.controller

import com.hexagonal.appservice.test.MyCustomInterface
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val myCustomInterface: MyCustomInterface
){

    @GetMapping("/ab")
    fun fdlad():String {
        myCustomInterface.a()
        return "index.html"
    }


}