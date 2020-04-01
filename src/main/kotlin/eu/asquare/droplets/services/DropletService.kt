package eu.asquare.droplets.services

import eu.asquare.droplets.data.Droplet
import eu.asquare.droplets.data.DropletRepository
import eu.asquare.droplets.data.UserRepository
import eu.asquare.droplets.presentation.DropletResource
import org.springframework.stereotype.Service

@Service
class DropletService(
    private val dropletRepository: DropletRepository,
    private val userRepository: UserRepository,
    private val urlInfoService: UrlInfoService
) {
    fun create(url: String, userName: String) {
        val user = userRepository.findByName(userName)
        val urlInfo = urlInfoService.getUrlInfo(url)
        val droplet = Droplet(urlInfo).also { it.user = user }

        dropletRepository.save(droplet)
    }

    fun getAll() = dropletRepository.findAll().map { droplet ->
        val shortUrl = urlInfoService.getShortUrl(droplet.url)
        DropletResource(droplet).also { it.shortUrl = shortUrl }
    }.sortedByDescending { it.created }
}