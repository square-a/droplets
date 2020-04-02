package eu.asquare.droplets.data

import javax.persistence.*

@Entity
@Table(name = "groups")
class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    val id: Long,

    @Column(columnDefinition = "SMALLINT UNSIGNED")
    val code: Int,

    @OneToMany(mappedBy = "group")
    val users: Set<User> = setOf(),

    @OneToMany(mappedBy = "group")
    val droplets: Set<Droplet> = setOf()
)