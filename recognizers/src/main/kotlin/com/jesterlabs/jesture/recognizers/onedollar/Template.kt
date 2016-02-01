package com.jesterlabs.jesture.recognizers.onedollar

import com.jesterlabs.jesture.recognizers.common.data.Point
import com.jesterlabs.jesture.recognizers.onedollar.util.*

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