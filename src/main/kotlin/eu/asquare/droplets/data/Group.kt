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
    val code: Short,

    @OneToMany(mappedBy = "group")
    val users: Set<User> = setOf()
)