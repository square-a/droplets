package eu.asquare.droplets.services

import eu.asquare.droplets.data.Droplet
import eu.asquare.droplets.data.DropletRepository
import eu.asquare.droplets.presentation.DropletResource
import org.springframework.stereotype.Service

@Service
class DropletService(
    private val dropletRepository: DropletRepository
) {
    fun create(resource: DropletResource) {
        val droplet = Droplet(resource)

        dropletRepository.save(droplet)
    }

    fun getAll() = dropletRepository.findAll().map {
        DropletResource(
            it
        )
    }
}