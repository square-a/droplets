package eu.asquare.droplets.services

import eu.asquare.droplets.data.UserRepository
import eu.asquare.droplets.security.DropletUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class DropletUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByName(username) ?: throw UsernameNotFoundException(username)

        return DropletUser(user)
    }
}