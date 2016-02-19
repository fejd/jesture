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

package com.jesterlabs.jesture.recognizers.onedollar.util

import com.jesterlabs.jesture.recognizers.common.data.Point
import com.jesterlabs.jesture.recognizers.common.data.Rectangle
import com.jesterlabs.jesture.recognizers.common.util.centroid
import com.jesterlabs.jesture.recognizers.common.util.distance
import com.jesterlabs.jesture.recognizers.common.util.pathLength
import com.jesterlabs.jesture.recognizers.onedollar.Template
import java.util.*

val NUMPOINTS = 64
val SQUARE_SIZE = 250.0
val ANGLE_RANGE = 45.0
val ANGLE_PRECISION = 2.0
val HALF_DIAGONAL = 0.5 * Math.sqrt(250.0 * 250.0 + 250.0 * 250.0)
val PHI = 0.5 * (-1.0 + Math.sqrt(5.0)) // Golden Ratio

fun resample(points: List<Point>, numPoints: Int) : List<Point> {
    val I = pathLength(points) / (numPoints - 1.0);
    var D = 0.0;
    var newpoints = ArrayList<Point>();
    var stack = Stack<Point>();
    for (i in points.indices) {
        stack.push(points.get(points.size - 1 - i))
    }

    while(!stack.empty()) {
        val pt1 = stack.pop();

        if(stack.empty()) {
            newpoints.add(pt1)
            continue;
        }
        val pt2 = stack.peek();
        val d = distance(pt1, pt2);
        if( (D + d) >= I) {
            val qx = pt1.x + (( I - D ) / d ) * (pt2.x - pt1.x);
            val qy = pt1.y + (( I - D ) / d ) * (pt2.y - pt1.y);
            val q = Point(qx, qy);
            newpoints.add(q)
            stack.push(q);
            D = 0.0;
        } else {
            D += d;
        }
    }

    // Sometimes we fall a rounding-error short of adding the last point, so add it if so
    if(newpoints.size == (numPoints - 1)) {
        newpoints.add(points.last())
    }
    return newpoints;
}

fun indicativeAngle(points: List<Point>) : Double {
    var c = centroid(points);
    return Math.atan2((c.y - points.get(0).y),
            (c.x - points.get(0).x))
}

// rotates points around centroid
fun rotateBy(points: List<Point>, radians: Double) : List<Point> {
    var c = centroid(points);
    var cos = Math.cos(radians);
    var sin = Math.sin(radians);

    var newpoints = ArrayList<Point>();
    for (point in points) {
        var qx = (point.x - c.x) * cos - (point.y - c.y) * sin + c.x
        var qy = (point.x - c.x) * sin + (point.y - c.y) * cos + c.y;
        newpoints.add(Point(qx, qy))
    }

    return newpoints;
}

// non-uniform scale; assumes 2D gestures (i.e., no lines)
fun scaleTo(points: List<Point>, size: Double) : List<Point> {
    val B = boundingBox(points);
    var newpoints = ArrayList<Point>();
    for (point in points) {
        var qx = point.x * (size / B.width);
        var qy = point.y * (size / B.height);
        newpoints.add(Point(qx, qy))
    }

    return newpoints;
}

fun boundingBox(points: List<Point>) : Rectangle {
    var minX = Double.POSITIVE_INFINITY
    var maxX = Double.NEGATIVE_INFINITY
    var minY = Double.POSITIVE_INFINITY
    var maxY = Double.NEGATIVE_INFINITY

    for (i in points.indices) {
        if (i == 0) continue
        val point = points.get(i)
        minX = Math.min(point.x, minX)
        maxX = Math.max(point.x, maxX)
        minY = Math.min(point.y, minY)
        maxY = Math.max(point.y, maxY)
    }

    return Rectangle(minX, minY, maxX - minX, maxY - minY)
}

// translates points' centroid
fun translateTo(points: List<Point>, origin: Point) : List<Point> {
    var c = centroid(points);
    var newpoints = ArrayList<Point>();
    for (point in points) {
        var qx = point.x + origin.x - c.x
        var qy = point.y + origin.y - c.y
        newpoints.add(Point(qx, qy))
    }

    return newpoints;
}

fun distanceAtBestAngle(points: List<Point>, template: Template, a: Double, b: Double,
                        threshold: Double) : Double {
    var x1 = PHI * a + (1.0 - PHI) * b;
    var f1 = distanceAtAngle(points, template, x1);
    var x2 = (1.0 - PHI) * a + PHI * b;
    var f2 = distanceAtAngle(points, template, x2);
    var bb = b
    var aa = a
    while (Math.abs(bb - aa) > threshold) {
        if (f1 < f2) {
            bb = x2;
            x2 = x1;
            f2 = f1;
            x1 = PHI * aa + (1.0 - PHI) * bb;
            f1 = distanceAtAngle(points, template, x1);
        }
        else {
            aa = x1;
            x1 = x2;
            f1 = f2;
            x2 = (1.0 - PHI) * aa + PHI * bb;
            f2 = distanceAtAngle(points, template, x2);
        }
    }
    return Math.min(f1, f2);
}

fun distanceAtAngle(points: List<Point>, template: Template, radians: Double) : Double {
    var newpoints = rotateBy(points, radians);
    return pathDistance(newpoints, template.processedPoints);
}

fun pathDistance(points1: List<Point>, points2: List<Point>) : Double {
    if(points1.size != points2.size) {
        println("Lengths differ. " + points1.size + " != " + points2.size);
        return Double.POSITIVE_INFINITY;
    }
    var d = 0.0;
    for (i in points1.indices) {
        d += distance(points1.get(i), points2.get(i))
    }
    return d / points1.size;

}