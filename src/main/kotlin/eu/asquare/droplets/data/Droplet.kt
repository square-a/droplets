package eu.asquare.droplets.data

import eu.asquare.droplets.services.UrlInfo
import javax.persistence.*

@Entity
@Table(name = "droplets")
data class Droplet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    val id: Long = 0,

    val url: String,
    val title: String,
    val description: String?,
    @Column(name = "image_url")
    val imageUrl: String?
) {
    constructor(urlInfo: UrlInfo) : this(
        0L,
        urlInfo.url,
        urlInfo.title,
        urlInfo.description,
        urlInfo.imageUrl
    )
}