package com.minjae.kotlin_laboratory.coroutine
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun threadTask() {
    Thread.sleep(10)
}

fun measureThreadPerformance() {
    val threadCount = 10000
    val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    val startTime = System.nanoTime()
    val startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()

    for (i in 0 until threadCount) {
        executor.submit {
            threadTask()
        }
    }

    executor.shutdown()
    executor.awaitTermination(20, TimeUnit.SECONDS)

    val endTime = System.nanoTime()
    val endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()

    println("Threads - Memory used: ${(endMemory - startMemory) / 1024} KB")
    println("Threads - Time taken: ${(endTime - startTime) / 1_000_000} ms")
}

fun main() {
    measureThreadPerformance()
}