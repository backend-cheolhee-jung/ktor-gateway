package com.example.news

import news.NewsResponse
import news.Station
import org.w3c.dom.Element
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilderFactory


object XmlParser {
    private val formatter = DateTimeFormatter.RFC_1123_DATE_TIME

    fun parse(value: String, station: Station): List<NewsResponse> {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(value.byteInputStream())
        val items = doc.getElementsByTagName(ITEM)

        val newsList = mutableListOf<NewsResponse>()

        for (i in 0 until items.length) {
            val item = items.item(i) as Element

            val title = item.getElementsByTagName(TITLE).item(0).textContent.trim()
            val description = item.getElementsByTagName(DESCRIPTION).item(0).textContent.trim()

            val pubDateText = item.getElementsByTagName(PUBLISHED_AT).item(0)?.textContent
            val publishedAt = pubDateText?.let { LocalDateTime.parse(it, formatter) }

            val imageUrl = (item.getElementsByTagName(MEDIA_THUMBNAIL).item(0) as? Element)
                ?.getAttribute(URL) ?: EMPTY

            newsList.add(
                NewsResponse(
                    title = title,
                    station = station,
                    description = description,
                    imageUrl = imageUrl,
                    publishedAt = publishedAt
                )
            )
        }

        return newsList
    }

    private const val ITEM = "item"
    private const val TITLE = "title"
    private const val DESCRIPTION = "description"
    private const val PUBLISHED_AT = "pubDate"
    private const val MEDIA_THUMBNAIL = "media:thumbnail"
    private const val URL = "url"
    private const val EMPTY = ""
}