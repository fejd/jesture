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
import java.util.*

/**
 * Implementation of the $1 Unistroke Recognizer algorithm.
 *
 * The $1 Unistroke Recognizer is a simple yet effective gesture recognition algorithm
 * developed by Jacob O. Wobbrock, Andrew D. Wilson, and Yang Li. It recognizes single-stroke
 * gestures (unistroke patterns) by comparing user input against a set of predefined templates.
 *
 * The algorithm works by normalizing both the input gesture and template gestures through
 * a series of transformations (resampling, rotation, scaling, and translation), then finding
 * the best match using a distance metric.
 *
 * By default, the recognizer is initialized with 16 common gesture templates including:
 * TRIANGLE, X, RECTANGLE, CIRCLE, CHECK, CARET, ZIGZAG, ARROW, LEFT_SQUARE_BRACKET,
 * RIGHT_SQUARE_BRACKET, V, DELETE, LEFT_CURLY_BRACE, RIGHT_CURLY_BRACE, STAR, and PIGTAIL.
 *
 * ## Usage Example
 * ```kotlin
 * // Create recognizer with default templates
 * val recognizer = OneDollarRecognizer()
 *
 * // Capture user input as a list of points
 * val userGesture = listOf(
 *     Point(100.0, 100.0),
 *     Point(150.0, 150.0),
 *     // ... more points
 * )
 *
 * // Recognize the gesture
 * val result = recognizer.recognize(userGesture)
 * println("Recognized: ${result.name} with confidence ${result.score}")
 * ```
 *
 * @see Template
 * @see Result
 * @see Point
 */
class OneDollarRecognizer {
    var templates = ArrayList<Template>()
    init {
        templates = DollarStrokeFactory().createDefaultStrokes()
    }

    /**
     * Adds a custom template to the recognizer's template set.
     *
     * Custom templates allow you to extend the recognizer with application-specific gestures
     * beyond the default set. The template will be automatically preprocessed (resampled,
     * rotated, scaled, and translated) when created.
     *
     * @param template The template to add to the recognition set
     * @see Template
     */
    fun addTemplate(template: Template) {
        templates.add(template)
    }

    /**
     * Recognizes a gesture from a list of points.
     *
     * This method processes the input points through the $1 algorithm's normalization steps:
     * 1. **Resample** to 64 evenly-spaced points
     * 2. **Rotate** to align with the indicative angle
     * 3. **Scale** to a standard 250x250 square
     * 4. **Translate** to move the centroid to the origin
     * 5. **Match** against all templates using golden section search
     *
     * The gesture is compared against all templates in the template set, and the best match
     * (lowest distance) is returned.
     *
     * ## Requirements
     * - At least 2 points required (though 64+ recommended for accuracy)
     * - Points should represent a continuous stroke in temporal order
     * - Coordinates can be in any scale (will be normalized)
     *
     * ## Score Interpretation
     * - Score ranges from 0.0 (no match) to 1.0 (perfect match)
     * - Scores above 0.90 typically indicate strong matches
     * - Scores below 0.70 may indicate poor or incorrect matches
     *
     * @param points The list of points representing the user's gesture, in temporal order
     * @return A [Result] containing the name of the best-matching template and a confidence score (0.0-1.0)
     * @see Result
     * @see Point
     */
    fun recognize(points: List<Point>) : Result {
        var processedPoints = resample(points, NUMPOINTS);
        val radians = indicativeAngle(points);
        processedPoints = rotateBy(processedPoints, -radians);
        processedPoints = scaleTo(processedPoints, SQUARE_SIZE);
        val origin = Point(0.0, 0.0);
        processedPoints = translateTo(processedPoints, origin);
        //var vector = Vectorize(points); // for Protractor

        var b = Double.POSITIVE_INFINITY;
        var foundTemplateName = "";
        for (template in this.templates) {
            val d = distanceAtBestAngle(processedPoints, template, -ANGLE_RANGE, ANGLE_RANGE, ANGLE_PRECISION)

            if (d < b) {
                b = d; // best (least) distance
                foundTemplateName = template.name
            }
        }

        return Result(foundTemplateName, 1.0 - b / HALF_DIAGONAL);
    }
}
