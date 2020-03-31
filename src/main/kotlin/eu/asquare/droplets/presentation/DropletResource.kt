package eu.asquare.droplets.presentation

import eu.asquare.droplets.data.Droplet

data class DropletResource(
    val id: Long = 0,
    val url: String,
    val title: String?,
    val description: String?,
    val imageUrl: String?
) {
    constructor(droplet: Droplet) : this(
        droplet.id,
        droplet.url,
        droplet.title,
        droplet.description,
        droplet.imageUrl
    )
}