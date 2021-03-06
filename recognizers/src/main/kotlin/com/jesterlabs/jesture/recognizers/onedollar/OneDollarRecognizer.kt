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

class OneDollarRecognizer {
    var templates = ArrayList<Template>()
    init {
        templates = DollarStrokeFactory().createDefaultStrokes()
    }

    fun addTemplate(template: Template) {
        templates.add(template)
    }

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
