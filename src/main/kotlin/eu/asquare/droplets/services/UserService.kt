package eu.asquare.droplets.services

import eu.asquare.droplets.data.Group
import eu.asquare.droplets.data.User
import eu.asquare.droplets.data.UserRepository
import eu.asquare.droplets.presentation.UserResource
import org.springframework.stereotype.Service
import javax.persistence.EntityExistsException

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun create(group: Group, name: String = "admin-${group.id}"): User {
        if (userRepository.findByName(name) != null) {
            throw EntityExistsException("Username $name already taken")
        }
        return userRepository.save(User(0L, name, group))
    }

    fun getOne(username: String) = userRepository.findByName(username)

    fun getGroupUsers(group: Group) = group.users.map { UserResource(it) }

    fun updateOne(id: Long, username: String, group: Group) {
        val user = userRepository.findById(id).run {
            if (isPresent) get() else null
        }

        if (user != null && user.group == group) {
            if (userRepository.findByName(username) != null) {
                throw EntityExistsException("Username $username already taken")
            }

            user.name = username
            userRepository.save(user)
        }
    }
}