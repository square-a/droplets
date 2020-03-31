package eu.asquare.droplets.presentation

import eu.asquare.droplets.data.Droplet

data class DropletResource(
    val id: Long = 0,
    val url: String
) {
    constructor(droplet: Droplet) : this(
        droplet.id,
        droplet.url
    )
}