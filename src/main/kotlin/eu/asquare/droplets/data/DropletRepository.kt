package eu.asquare.droplets.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DropletRepository : JpaRepository<Droplet, Long> {
    fun findAllByGroupIdAndIsReadFalse(groupId: Long): List<Droplet>
    fun findAllByGroupId(groupId: Long): List<Droplet>
}