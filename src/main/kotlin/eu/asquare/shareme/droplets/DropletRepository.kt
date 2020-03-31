package eu.asquare.shareme.droplets

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DropletRepository: JpaRepository<Droplet, Long>