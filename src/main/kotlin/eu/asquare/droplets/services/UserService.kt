package eu.asquare.droplets.services

import eu.asquare.droplets.data.Group
import eu.asquare.droplets.data.User
import eu.asquare.droplets.data.UserRepository
import eu.asquare.droplets.presentation.UserResource
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun create(group: Group) = userRepository.save(User(0L, "admin", group))

    fun getOne(username: String) = userRepository.findByName(username)

    fun getGroupUsers(group: Group) = group.users.map { UserResource(it) }

    fun updateOne(id: Long, username: String, group: Group) {
        val user = userRepository.findById(id).run {
            if (isPresent) get() else null
        }

        if (user != null && user.group == group) {
            user.name = username

            userRepository.save(user)
        }
    }
}