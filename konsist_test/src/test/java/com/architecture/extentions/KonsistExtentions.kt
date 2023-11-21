package com.architecture.extentions

fun String.appendShouldHaveTest(): String{
    return "$this should have test"
}

fun String.appendShouldResideIn(packageStructure: String): String{
    return "$this should reside in $packageStructure package"
}
