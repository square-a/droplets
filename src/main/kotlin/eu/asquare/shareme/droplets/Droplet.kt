package eu.asquare.shareme.droplets

import javax.persistence.*

@Entity
@Table(name = "droplets")
data class Droplet(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "INT UNSIGNED")
        val id: Long = 0,

        val url: String? = null
) {
  constructor(resource: DropletResource): this(
          resource.id,
          resource.url
  )
}