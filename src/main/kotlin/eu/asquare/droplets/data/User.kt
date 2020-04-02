package eu.asquare.droplets.data

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    val id: Long,
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    val group: Group,

    @OneToMany(mappedBy = "user")
    val droplets: Set<Droplet> = setOf()
)