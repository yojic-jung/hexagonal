import com.hexagonal.appdomain.annotation.Aliases
import com.hexagonal.appdomain.annotation.UseCase
import com.hexagonal.appservice.member.port.inp.MemberSaveService

@Aliases("secondary")
@UseCase
class MemberSaveSecondaryService: MemberSaveService {

}
