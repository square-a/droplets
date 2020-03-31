package eu.asquare.shareme.droplets

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(path = ["droplets"])
class DropletController(
        private val dropletService: DropletService
) {

  @GetMapping
  fun listDroplets(model: Model): String {
    model["title"] = "Droplets"
    return "droplets"
  }

  @PostMapping
  fun createDroplet(
          @ModelAttribute
          resource: DropletResource
  ): String {
    dropletService.create(resource)
    return "redirect:/droplets"
  }
}