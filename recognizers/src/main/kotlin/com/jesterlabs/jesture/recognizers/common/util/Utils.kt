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

package com.jesterlabs.jesture.recognizers.common.util

import com.jesterlabs.jesture.recognizers.common.data.Point
import java.util.*

fun ArrayList<Double>.std(avg : Double) : Double {
    var sum = 0.0
    for (d in this) {
        sum += Math.pow(d - avg, 2.0)
    }
    return Math.sqrt(sum / this.size)
}

fun distance(firstPoint: Point, secondPoint: Point) : Double {
    val dx = firstPoint.x - secondPoint.x
    val dy = firstPoint.y - secondPoint.y
    return Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0))
}

fun pathLength(points: List<Point>) : Double {
    var distance = 0.0

    for (i in points.indices) {
        if (i == 0) continue
        val first = points.get(i-1)
        val second = points.get(i)
        distance += distance(first, second)
    }
    return distance
}

fun centroid(points: List<Point>) : Point {
    var x = 0.0
    var y = 0.0
    for (i in points) {
        x += i.x
        y += i.y
    }
    x /= points.size;
    y /= points.size;
    return Point(x, y);
}