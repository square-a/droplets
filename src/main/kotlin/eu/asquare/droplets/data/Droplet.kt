package eu.asquare.droplets.data

import eu.asquare.droplets.services.UrlInfo
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp
import java.time.LocalDateTime
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
    val imageUrl: String?,

    @CreationTimestamp
    val created: LocalDateTime = LocalDateTime.now()
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    constructor(urlInfo: UrlInfo) : this(
        0L,
        urlInfo.url,
        urlInfo.title,
        urlInfo.description,
        urlInfo.imageUrl
    )
}