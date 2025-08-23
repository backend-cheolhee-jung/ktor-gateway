package com.example.plugin.module

import com.example.datafetcher.datafetcher.ExchangeRateDataFetcher
import org.koin.dsl.module

val exchangeRateModule = module {
    single<ExchangeRateDataFetcher> { ExchangeRateDataFetcher(get()) }
}