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
import com.jesterlabs.jesture.recognizers.onedollar.util.*

/**
 * Represents a gesture template for the $1 Unistroke Recognizer.
 *
 * A template stores a reference gesture pattern that user input will be compared against.
 * Upon creation, the template automatically preprocesses the input points through the
 * same normalization steps used during recognition:
 * 1. Resampling to 64 evenly-spaced points
 * 2. Rotation to align with the indicative angle
 * 3. Scaling to a standard 250x250 square
 * 4. Translation to move the centroid to the origin
 *
 * This preprocessing ensures that templates are in a normalized form for efficient
 * comparison during recognition.
 *
 * ## Usage Example
 * ```kotlin
 * // Create a custom triangle template
 * val trianglePoints = listOf(
 *     Point(100.0, 200.0),
 *     Point(200.0, 200.0),
 *     Point(150.0, 100.0),
 *     Point(100.0, 200.0)  // Close the shape
 * )
 * val template = Template("MyTriangle", trianglePoints)
 *
 * // Add to recognizer
 * recognizer.addTemplate(template)
 * ```
 *
 * @property name The identifying name of this gesture template (e.g., "CIRCLE", "TRIANGLE")
 * @property points The raw input points defining the gesture pattern
 * @property processedPoints The normalized points after preprocessing, used for comparison during recognition
 * @see OneDollarRecognizer
 * @see Point
 */
class Template(val name: String, val points: List<Point>) {
    var processedPoints = points
    init {
        processedPoints = resample(points, NUMPOINTS)
        val radians = indicativeAngle(processedPoints)
        processedPoints = rotateBy(processedPoints, radians)
        processedPoints = scaleTo(processedPoints, SQUARE_SIZE)
        val origin = Point(0.0, 0.0);
        processedPoints = translateTo(processedPoints, origin)
        // Use Protractor?
        /*
        this.Vector = Vectorize(this.Points); // for Protractor*/
    }
}