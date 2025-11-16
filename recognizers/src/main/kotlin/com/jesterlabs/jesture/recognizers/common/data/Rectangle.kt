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
 * Represents an axis-aligned bounding rectangle.
 *
 * Rectangles are used internally by the $1 algorithm to compute bounding boxes
 * for gesture normalization and scaling operations.
 *
 * ## Coordinate System
 * - **x, y**: The top-left corner of the rectangle
 * - **width**: The horizontal extent of the rectangle
 * - **height**: The vertical extent of the rectangle
 *
 * ## Usage Example
 * ```kotlin
 * // Create a rectangle
 * val bounds = Rectangle(x = 10.0, y = 20.0, width = 100.0, height = 50.0)
 *
 * // The rectangle extends from (10, 20) to (110, 70)
 * ```
 *
 * @property x The x-coordinate of the top-left corner
 * @property y The y-coordinate of the top-left corner
 * @property width The width of the rectangle (horizontal extent)
 * @property height The height of the rectangle (vertical extent)
 */
data class Rectangle(val x: Double, val y: Double, val width: Double, val height: Double)