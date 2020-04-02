package eu.asquare.droplets.controllers

import eu.asquare.droplets.presentation.DropletFilter
import eu.asquare.droplets.presentation.DropletFormData
import eu.asquare.droplets.services.DropletService
import eu.asquare.droplets.services.UserService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(path = ["/"])
class DropletController(
    private val dropletService: DropletService,
    private val userService: UserService
) {

    @GetMapping
    fun showDroplets(
        authentication: Authentication,
        model: Model,
        @RequestParam
        showAll: Boolean = false
    ): String {
        val user = userService.getOne(authentication.name)
        if (user != null) {
            val filter = DropletFilter(!showAll)
            model["filter"] = filter
            model["droplets"] = dropletService.getMany(user.group.id, filter)
        }

        model["page"] = "droplets"
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

    @PostMapping(value = ["droplets/{id}"])
    @ResponseBody
    fun markDropletRead(
        @PathVariable
        id: Long
    ) {
        dropletService.markAsRead(id)
    }
}