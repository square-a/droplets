package eu.asquare.shareme.droplets

import org.springframework.stereotype.Service

@Service
class DropletService(
        private val dropletRepository: DropletRepository
) {
  fun create(resource: DropletResource) {
    val droplet = Droplet(resource)

    dropletRepository.save(droplet)
  }

  fun getAll() = dropletRepository.findAll().map { DropletResource(it) }
}