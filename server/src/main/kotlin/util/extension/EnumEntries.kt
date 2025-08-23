package com.example.util.extension

import kotlin.enums.EnumEntries

fun <T> EnumEntries<T>.exclude(vararg value: T): List<T> where T : Enum<T> {
    return this.filter { it !in value }
}