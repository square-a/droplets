package eu.asquare.droplets.services

import com.rometools.rome.feed.synd.SyndContentImpl
import com.rometools.rome.feed.synd.SyndEntryImpl
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.feed.synd.SyndFeedImpl
import eu.asquare.droplets.presentation.DropletFilter
import org.springframework.stereotype.Service
import java.time.ZoneOffset
import java.util.*

@Service
class FeedService(
    private val dropletService: DropletService,
    private val groupService: GroupService
) {
    private val outputFeedType = "rss_2.0"
    private val outputTitle = "Droplets"

    fun get(groupCode: Int): SyndFeed? {
        val feed = SyndFeedImpl().apply {
            feedType = outputFeedType
            title = outputTitle
        }
        val filter = DropletFilter(onlyUnread = true)
        val group = groupService.get(groupCode) ?: return null
        val droplets = dropletService.getManyEntities(group.id, filter)

        feed.entries = droplets.map { droplet ->
            SyndEntryImpl().apply {
                title = droplet.title
                link = droplet.url
                publishedDate = Date.from(droplet.created.toInstant(ZoneOffset.UTC))
                description = SyndContentImpl().also {
                    it.type = "text/plain"
                    it.value = droplet.description
                }
            }
        }

        return feed
    }
}