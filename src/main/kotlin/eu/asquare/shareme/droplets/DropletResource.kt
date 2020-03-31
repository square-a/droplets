package eu.asquare.shareme.droplets

data class DropletResource(
        val id: Long = 0,
        val url: String
) {
  constructor(droplet: Droplet): this(
          droplet.id,
          droplet.url
  )
}