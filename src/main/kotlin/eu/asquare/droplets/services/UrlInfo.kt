package eu.asquare.droplets.services

data class UrlInfo(
    val url: String,
    val shortUrl: String,
    val title: String,
    val description: String?,
    val imageUrl: String?
)