package eu.asquare.droplets.data

import eu.asquare.droplets.presentation.DropletResource
import javax.persistence.*

@Entity
@Table(name = "droplets")
data class Droplet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    val id: Long = 0,

    val url: String
) {
    constructor(resource: DropletResource) : this(
        resource.id,
        resource.url
    )
}