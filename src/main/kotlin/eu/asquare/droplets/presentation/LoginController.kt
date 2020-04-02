package eu.asquare.droplets.presentation

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["login"])
class LoginController {

    @GetMapping
    fun showLoginForm(model: Model) = "login"
}