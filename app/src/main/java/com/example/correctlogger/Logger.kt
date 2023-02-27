package com.example.correctlogger

import android.content.Context
import android.util.Log

interface Logger {

    fun log(message: String)

    class Console(private val tag: String) : Logger {
        override fun log(message: String) {
            Log.d(tag, message)
        }
    }

    class Remote(private val api: Api) : Logger {
        override fun log(message: String) {
            api.sendLog(message)
        }
    }

    class File(
        private val fileName: String,
        private val context: Context
    ) : Logger {
        override fun log(message: String) {
            context.openFileInput(fileName).use {
                //someCode
            }
        }
    }

    class Combo(
        private val loggerRemote: Logger,
        private val loggerLocal: Logger
    ) : Logger {
        override fun log(message: String) {
            loggerRemote.log(message)
            loggerLocal.log(message)
        }
    }
}

interface Api {
    fun sendLog(message: String)
}