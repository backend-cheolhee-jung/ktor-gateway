package com.example.news

import news.NewsResponse
import news.Station
import org.w3c.dom.Element
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilderFactory

interface XmlParser {
    fun parse(station: Station, value: String): List<NewsResponse>
}

class BbcXmlParser: XmlParser {
    override fun parse(station: Station, value: String): List<NewsResponse> {
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
            val publishedAt = pubDateText?.let { LocalDateTime.parse(it, DateTimeFormatter.RFC_1123_DATE_TIME) }

            val mediaThumbnail = item.getElementsByTagName(MEDIA_THUMBNAIL).item(0) as? Element
            val imageUrl = mediaThumbnail?.getAttribute(URL) ?: EMPTY

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

    private companion object {
        const val ITEM = "item"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val PUBLISHED_AT = "pubDate"
        const val MEDIA_THUMBNAIL = "media:thumbnail"
        const val URL = "url"
        const val EMPTY = ""
    }
}

class NytXmlParser: XmlParser {
    override fun parse(station: Station, value: String): List<NewsResponse> {
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
            val publishedAt = pubDateText?.let { LocalDateTime.parse(it, DateTimeFormatter.RFC_1123_DATE_TIME) }

            val mediaContent = item.getElementsByTagName(MEDIA_CONTENT).item(0) as? Element
            val imageUrl = mediaContent?.getAttribute(URL) ?: EMPTY

            newsList.add(
                NewsResponse(
                    title = title,
                    station = station,
                    description = description,
                    imageUrl = imageUrl,
                    publishedAt = publishedAt,
                )
            )
        }

        return newsList
    }

    private companion object {
        const val ITEM = "item"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val PUBLISHED_AT = "pubDate"
        const val CREATOR = "dc:creator"
        const val MEDIA_CONTENT = "media:content"
        const val URL = "url"
        const val EMPTY = ""
    }
}