package com.samridhi.jobsyapp.util

import androidx.compose.foundation.lazy.LazyListState

fun String.addRouteArgument(argName: String) =
    this.plus("?").plus(argName).plus("={").plus(argName).plus("}")

fun String.withArg(argName: String, argValue: String) =
    "$this?$argName=${argValue}"

fun LazyListState.isScrolledToTheEnd(): Boolean {
    val layoutInfo = this.layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo
    return visibleItems.isNotEmpty() && visibleItems.last().index == layoutInfo.totalItemsCount - 1
}
