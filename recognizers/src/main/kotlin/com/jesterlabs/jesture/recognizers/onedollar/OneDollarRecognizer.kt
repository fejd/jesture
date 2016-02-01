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
