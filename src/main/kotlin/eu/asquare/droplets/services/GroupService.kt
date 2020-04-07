package eu.asquare.droplets.services

import eu.asquare.droplets.data.Group
import eu.asquare.droplets.data.GroupRepository
import org.springframework.stereotype.Service

@Service
class GroupService(
    private val groupRepository: GroupRepository
) {
    private val minCode = 10000
    private val maxCode = 65535

    fun isInitialGroup() = groupRepository.count() == 0L

    // NOTE: with some really bad luck this might already break for the second group
    fun create(): Group {
        val code = (minCode..maxCode).shuffled().first()
        val group = Group(0L, code)

        return groupRepository.save(group)
    }

    fun get(groupCode: Int) = groupRepository.findByCode(groupCode)
}