package eu.asquare.droplets.services

import eu.asquare.droplets.data.Group
import eu.asquare.droplets.data.User
import eu.asquare.droplets.data.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun create(group: Group) = userRepository.save(User(0L, "admin", group))
}