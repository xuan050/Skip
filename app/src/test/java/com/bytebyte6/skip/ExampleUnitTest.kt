package com.bytebyte6.skip

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println("跳过广告 | 4s".length)
        println(System.currentTimeMillis()/1000)
        assertEquals(4, 2 + 2)
    }
}