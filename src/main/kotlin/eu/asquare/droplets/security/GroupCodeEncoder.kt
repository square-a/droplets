package eu.asquare.droplets.security

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class GroupCodeEncoder : PasswordEncoder {
    override fun encode(groupCode: CharSequence?) = groupCode.toString()

    override fun matches(groupCode: CharSequence?, encodedPassword: String?) =
        groupCode.toString() == encodedPassword
}