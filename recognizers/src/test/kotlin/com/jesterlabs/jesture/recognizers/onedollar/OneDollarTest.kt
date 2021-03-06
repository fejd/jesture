/*
 * Copyright (c) 2016 Fredrik Henricsson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.jesterlabs.jesture.recognizers.onedollar

import com.jesterlabs.jesture.recognizers.common.data.Point
import org.junit.Test
import java.util.*

/**
 * Tests for the $1 Unistroke recognizer
 */
class OneDollarTest {
    @Test fun testRecognizeCheck() {
        val points = ArrayList<Point>()
        points.addAll(arrayListOf(Point(91.0, 185.0), Point(93.0, 185.0), Point(95.0, 185.0), Point(97.0, 185.0), Point(100.0, 188.0), Point(102.0, 189.0), Point(104.0, 190.0), Point(106.0, 193.0), Point(108.0, 195.0), Point(110.0, 198.0), Point(112.0, 201.0), Point(114.0, 204.0), Point(115.0, 207.0), Point(117.0, 210.0), Point(118.0, 212.0), Point(120.0, 214.0), Point(121.0, 217.0), Point(122.0, 219.0), Point(123.0, 222.0), Point(124.0, 224.0), Point(126.0, 226.0), Point(127.0, 229.0), Point(129.0, 231.0), Point(130.0, 233.0), Point(129.0, 231.0), Point(129.0, 228.0), Point(129.0, 226.0), Point(129.0, 224.0), Point(129.0, 221.0), Point(129.0, 218.0), Point(129.0, 212.0), Point(129.0, 208.0), Point(130.0, 198.0), Point(132.0, 189.0), Point(134.0, 182.0), Point(137.0, 173.0), Point(143.0, 164.0), Point(147.0, 157.0), Point(151.0, 151.0), Point(155.0, 144.0), Point(161.0, 137.0), Point(165.0, 131.0), Point(171.0, 122.0), Point(174.0, 118.0), Point(176.0, 114.0), Point(177.0, 112.0), Point(177.0, 114.0), Point(175.0, 116.0), Point(173.0, 118.0)))
        val result = OneDollarRecognizer().recognize(points)
        assert(result.name.isNotEmpty())
        assert(result.score > 0.94)
    }

    @Test fun testRecognizeFail() {
        val points = ArrayList<Point>()
        points.addAll(arrayListOf(Point(91.0, 185.0), Point(93.0, 185.0)))
        val result = OneDollarRecognizer().recognize(points)
        assert(result.name.isEmpty())
        assert(result.score == Double.NEGATIVE_INFINITY)
    }
}
