package eu.asquare.droplets.presentation

import eu.asquare.droplets.data.User

data class UserResource(
    val id: Long,
    val name: String
) {
    constructor(user: User) : this(
        user.id,
        user.name
    )
}