package com.mtz.core.utils

import android.os.Build

fun isAndroid10(): Boolean {
    return Build.VERSION.SDK_INT == Build.VERSION_CODES.Q
}

fun isGreaterOrEqualAndroid10(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}

fun isAndroid11(): Boolean {
    return Build.VERSION.SDK_INT == Build.VERSION_CODES.R
}

fun isGreaterOrEqualAndroid11(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
}

fun isAndroid6(): Boolean {
    return Build.VERSION.SDK_INT == Build.VERSION_CODES.M
}

fun isGreaterOrEqualAndroid6(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}

fun isAndroid8(): Boolean {
    return Build.VERSION.SDK_INT == Build.VERSION_CODES.O
}

fun isGreaterOrEqualAndroid8(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}