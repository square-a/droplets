package eu.asquare.droplets.presentation

import eu.asquare.droplets.services.DropletService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["/"])
class DropletController(
    private val dropletService: DropletService
) {

    @GetMapping
    fun listDroplets(model: Model): String {
        model["droplets"] = dropletService.getAll()
        return "droplets"
    }

    @PostMapping
    fun createDroplet(
        authentication: Authentication,
        @ModelAttribute
        formData: DropletFormData
    ): String {
        dropletService.create(formData.url, authentication.name)
        return "redirect:/"
    }
}