package com.example.plugin.module

import com.example.datafetcher.datafetcher.WeatherDataFetcher
import org.koin.dsl.module

val weatherModule = module {
    single<WeatherDataFetcher> { WeatherDataFetcher(get()) }
}