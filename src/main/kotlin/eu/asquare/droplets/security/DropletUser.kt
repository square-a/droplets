package eu.asquare.droplets.security

import eu.asquare.droplets.data.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class DropletUser(
    private val user: User
) : UserDetails {
    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority("User"))

    override fun getUsername() = user.name
    override fun getPassword() = user.group.code.toString()

    override fun isEnabled() = true
    override fun isCredentialsNonExpired() = true
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}