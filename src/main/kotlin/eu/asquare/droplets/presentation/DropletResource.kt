package eu.asquare.droplets.presentation

import eu.asquare.droplets.data.Droplet
import java.time.format.DateTimeFormatter

data class DropletResource(
    val id: Long = 0,
    val url: String,
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val created: String,
    val isRead: Boolean,
    val userName: String,
    var shortUrl: String? = null
) {
    constructor(droplet: Droplet) : this(
        droplet.id,
        droplet.url,
        droplet.title,
        droplet.description,
        droplet.imageUrl,
        droplet.created.format(DateTimeFormatter.ofPattern("d MMM yy")),
        droplet.isRead,
        droplet.user?.name ?: ""
    )
}