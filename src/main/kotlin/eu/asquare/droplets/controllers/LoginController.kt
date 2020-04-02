package eu.asquare.droplets.controllers

import eu.asquare.droplets.services.GroupService
import eu.asquare.droplets.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["login"])
class LoginController(
    private val groupService: GroupService,
    private val userService: UserService
) {

    @GetMapping
    fun showLoginForm(model: Model): String {
        if (groupService.isInitialGroup()) {
            val group = groupService.create()
            val user = userService.create(group)

            model["groupCode"] = group.code
            model["username"] = user.name
        }

        model["page"] = "login"
        return "login"
    }
}