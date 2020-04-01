package eu.asquare.droplets.services

import eu.asquare.droplets.data.Droplet
import eu.asquare.droplets.data.DropletRepository
import eu.asquare.droplets.presentation.DropletResource
import org.springframework.stereotype.Service

@Service
class DropletService(
    private val dropletRepository: DropletRepository,
    private val urlInfoService: UrlInfoService
) {
    fun create(url: String) {
        val urlInfo = urlInfoService.getUrlInfo(url)
        val droplet = Droplet(urlInfo)

        dropletRepository.save(droplet)
    }

    fun getAll() = dropletRepository.findAll().map { droplet ->
        val shortUrl = urlInfoService.getShortUrl(droplet.url)
        DropletResource(droplet).also { it.shortUrl = shortUrl }
    }.sortedByDescending { it.created }
}