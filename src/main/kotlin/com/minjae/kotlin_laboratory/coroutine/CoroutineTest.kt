package com.minjae.kotlin_laboratory.coroutine

import kotlinx.coroutines.*

suspend fun coroutineTask() {
    var result = 0L
    for (i in 1..1_000_000) {
        result += i
    }
}

fun measureCoroutinePerformance() = runBlocking {
    val coroutineCount = 10000

    val startTime = System.nanoTime()
    val startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()

    val jobs = List(coroutineCount) {
        launch {
            coroutineTask()
        }
    }

    jobs.forEach { it.join() }

    val endTime = System.nanoTime()
    val endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()

    println("Coroutines - Memory used: ${(endMemory - startMemory) / 1024} KB")
    println("Coroutines - Time taken: ${(endTime - startTime) / 1_000_000} ms")
}

fun main() {
    measureCoroutinePerformance()
}
