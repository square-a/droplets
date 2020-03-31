package eu.asquare.droplets.services

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class UrlInfoService {
    private val httpRegex = Regex("""^https?://(www\.)?""")
    private val slugRegex = Regex("""/.*$""")

    fun getUrlInfo(url: String): UrlInfo {
        val document = Jsoup.connect(url).get()
        val shortUrl = getShortUrl(url)
        val title = getTitle(document)
        val imageUrl = getImageUrl(document)
        val description = getDescription(document)

        return UrlInfo(url, shortUrl, title, description, imageUrl)
    }

    private fun getShortUrl(url: String) =
        url.replace(httpRegex, "").replace(slugRegex, "")

    private fun getTitle(document: Document): String {
        document.select("meta[property=og:title]").firstOrNull()?.run {
            return attr("content")
        }
        document.select("title").firstOrNull()?.run {
            return ownText()
        }

        return "---"
    }

    private fun getDescription(document: Document): String? {
        document.select("meta[property=og:description]").firstOrNull()?.run {
            return attr("content")
        }
        document.select("meta[name=description]").firstOrNull()?.run {
            return attr("content")
        }

        return null
    }

    private fun getImageUrl(document: Document): String? {
        document.select("meta[property=og:image]").firstOrNull()?.run {
            return attr("content")
        }

        return null
    }
}