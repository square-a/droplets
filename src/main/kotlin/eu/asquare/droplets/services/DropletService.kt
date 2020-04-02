package eu.asquare.droplets.services

import eu.asquare.droplets.data.Droplet
import eu.asquare.droplets.data.DropletRepository
import eu.asquare.droplets.data.UserRepository
import eu.asquare.droplets.presentation.DropletFilter
import eu.asquare.droplets.presentation.DropletResource
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class DropletService(
    private val dropletRepository: DropletRepository,
    private val userRepository: UserRepository,
    private val urlInfoService: UrlInfoService
) {
    fun create(url: String, userName: String) {
        val user = userRepository.findByName(userName)
        val urlInfo = urlInfoService.getUrlInfo(url)
        val droplet = Droplet(urlInfo).also {
            it.user = user
            it.group = user?.group
        }

        dropletRepository.save(droplet)
    }

    fun getMany(groupId: Long, filter: DropletFilter) =
        if (filter.onlyUnread) {
            dropletRepository.findAllByGroupIdAndIsReadFalse(groupId)
        } else {
            dropletRepository.findAllByGroupId(groupId)
        }
            .sortedByDescending { it.created }
            .map { droplet ->
                val shortUrl = urlInfoService.getShortUrl(droplet.url)
                DropletResource(droplet).also { it.shortUrl = shortUrl }
            }

    fun markAsRead(id: Long) {
        val droplet = dropletRepository.findById(id).orElseThrow {
            EntityNotFoundException("No Droplet found for id $id")
        }
        droplet.isRead = true
        dropletRepository.save(droplet)
    }
}