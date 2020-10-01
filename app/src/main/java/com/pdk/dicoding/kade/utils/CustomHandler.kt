package com.pdk.dicoding.kade.utils


/**
 * Created by Budi Ardianata on 01/10/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */

class CustomHandler<out T>(private val content: T) {
    private var isHandled = false

    fun getContent(): T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            content
        }
    }
}