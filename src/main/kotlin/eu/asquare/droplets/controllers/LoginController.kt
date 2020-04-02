package eu.asquare.droplets.controllers

import eu.asquare.droplets.services.GroupService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["login"])
class LoginController(
    private val groupService: GroupService
) {

    @GetMapping
    fun showLoginForm(model: Model): String {
        if (groupService.isInitialGroup()) {
            model["groupCode"] = groupService.getInitialGroupCode()
        }
        return "login"
    }
}