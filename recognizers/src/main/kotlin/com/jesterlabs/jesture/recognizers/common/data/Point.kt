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

package com.jesterlabs.jesture.recognizers.common.data

/**
 * Represents a 2D point in Cartesian coordinate space.
 *
 * Points are the fundamental building blocks of gestures. A gesture is represented
 * as a sequence of points captured over time (e.g., from mouse movement or touch input).
 *
 * ## Coordinate System
 * - **Origin**: Top-left (0, 0) - standard screen coordinates
 * - **X-axis**: Increases to the right
 * - **Y-axis**: Increases downward
 *
 * ## Usage Example
 * ```kotlin
 * // Create a point
 * val point = Point(100.0, 150.0)
 *
 * // Build a gesture from touch events
 * val gesture = mutableListOf<Point>()
 * gesture.add(Point(event.x.toDouble(), event.y.toDouble()))
 * ```
 *
 * @property x The horizontal coordinate
 * @property y The vertical coordinate
 * @see Template
 * @see OneDollarRecognizer
 */
data class Point(val x: Double, val y: Double)