package eu.asquare.droplets.services

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class UrlInfoService {
    private val isValidUrlRegex = Regex("""^https?://(www\.)?(?>.*)""")
    private val httpRegex = Regex("""^https?://(www\.)?""")
    private val slugRegex = Regex("""/.*$""")
    private val maxTitleLength = 50
    private val maxDescriptionLength = 500

    fun getUrlInfo(input: String): UrlInfo {
        val url = getValidUrl(input)

        Jsoup.connect(url).get().also { document: Document ->
            return UrlInfo(
                url,
                title = getDocumentTitle(document).take(maxTitleLength),
                description = getDocumentDescription(document)?.take(maxDescriptionLength),
                imageUrl = getDocumentImageUrl(document)
            )
        }
    }

    fun getShortUrl(url: String) = url.replace(httpRegex, "").replace(slugRegex, "")

    private fun getValidUrl(input: String) = if (isValidUrlRegex.matches(input)) input else "https://$input"

    private fun getDocumentTitle(document: Document): String {
        document.select("meta[property=og:title]").firstOrNull()?.run {
            return attr("content")
        }
        document.select("title").firstOrNull()?.run {
            return ownText()
        }

        return "---"
    }

    private fun getDocumentDescription(document: Document): String? {
        document.select("meta[property=og:description]").firstOrNull()?.run {
            return attr("content")
        }
        document.select("meta[name=description]").firstOrNull()?.run {
            return attr("content")
        }

        return null
    }

    private fun getDocumentImageUrl(document: Document): String? {
        document.select("meta[property=og:image]").firstOrNull()?.run {
            return attr("content")
        }

        return null
    }
}