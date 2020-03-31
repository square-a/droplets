package eu.asquare.droplets.presentation

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute

@ControllerAdvice
class LayoutAdvice {

    @ModelAttribute(name = "layout")
    fun layout() = Layout()
}