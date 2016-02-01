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