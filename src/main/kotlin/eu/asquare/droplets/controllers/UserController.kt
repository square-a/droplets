package eu.asquare.droplets.controllers

import eu.asquare.droplets.presentation.DropletFormData
import eu.asquare.droplets.presentation.UserResource
import eu.asquare.droplets.services.UserService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(value = ["users"])
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun showUsers(
        authentication: Authentication,
        model: Model
    ): String {
        val user = userService.getOne(authentication.name)
        if (user != null) {
            model["users"] = userService.getGroupUsers(user.group)
        }

        model["page"] = "users"
        return "users"
    }

    @PostMapping
    fun createUser(
        authentication: Authentication,
        @ModelAttribute
        formData: UserResource
    ): String {
        val createdBy = userService.getOne(authentication.name)
        if (createdBy != null) {
            userService.create(createdBy.group, formData.name)
        }

        return "redirect:/users"
    }

    @PostMapping(value = ["{id}"])
    fun updateUser(
        authentication: Authentication,
        @RequestParam
        id: Long,
        @ModelAttribute
        formData: UserResource
    ): String {
        val changedBy = userService.getOne(authentication.name)

        if (changedBy != null) {
            userService.updateOne(id, formData.name, changedBy.group)

            if (changedBy.id == id) {
                return "redirect:/logout"
            }
        }

        return "redirect:/users"
    }
}