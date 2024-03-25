package com.hexagonal.systemintegration.config

import com.hexagonal.appdomain.annotation.UseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

/**
 * application layer 전용 @Service annotation 대체용
 * - @UseCase 검색 & bean 등록을 요청 to spring
 * - (application layer 비위치시는 이유는, spring lib 의존 제거 목적)
 */
@Configuration
@ComponentScan(
    basePackages = ["com.hexagonal.appservice"],
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = arrayOf(UseCase::class),
        )
    ],
)
class UseCaseConfig
