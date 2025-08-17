package com.example.plugin.module

import com.example.datafetcher.datafetcher.NewsDataFetcher
import org.koin.dsl.module

val newsModule = module {
    single<NewsDataFetcher> { NewsDataFetcher(get()) }
}