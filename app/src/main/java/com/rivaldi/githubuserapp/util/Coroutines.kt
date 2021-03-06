package com.rivaldi.githubuserapp.util

import kotlinx.coroutines.*

object Coroutines {

    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    fun main(job: Job, work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main + job).launch {
            work()
        }

    fun io(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

}