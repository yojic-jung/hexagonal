package com.hexagonal.appservice.member.usecase

import com.hexagonal.appdomain.annotation.Priority
import com.hexagonal.appdomain.annotation.UseCase
import com.hexagonal.appservice.member.port.inp.MemberSaveService

@Priority
@UseCase
class MemberSaveDefaultService: MemberSaveService {

}
