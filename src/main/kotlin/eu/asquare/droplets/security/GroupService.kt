package eu.asquare.droplets.security

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

    fun getInitialGroupCode(): Int {
        val code = (minCode..maxCode).shuffled().first()
        val group = Group(0L, code)

        groupRepository.save(group).let {
            return it.code
        }
    }
}