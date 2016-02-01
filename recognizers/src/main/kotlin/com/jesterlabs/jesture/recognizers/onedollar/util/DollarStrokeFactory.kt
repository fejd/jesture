package com.jesterlabs.jesture.recognizers.onedollar.util

import com.jesterlabs.jesture.recognizers.common.data.Point
import com.jesterlabs.jesture.recognizers.common.util.StrokeFactory
import com.jesterlabs.jesture.recognizers.onedollar.Template
import java.util.*

class DollarStrokeFactory : StrokeFactory() {

    fun createStroke(name: String, points: ArrayList<Point>) : Template {
        return Template(name, points)
    }

    fun createDefaultStrokes() : ArrayList<Template> {
        var templates = ArrayList<Template>()
        templates.add(createStroke(StrokeTemplate.TRIANGLE.name, getStrokePoints(StrokeTemplate.TRIANGLE)))
        templates.add(createStroke(StrokeTemplate.X.name, getStrokePoints(StrokeTemplate.X)))
        templates.add(createStroke(StrokeTemplate.RECTANGLE.name, getStrokePoints(StrokeTemplate.RECTANGLE)))
        templates.add(createStroke(StrokeTemplate.CIRCLE.name, getStrokePoints(StrokeTemplate.CIRCLE)))
        templates.add(createStroke(StrokeTemplate.CHECK.name, getStrokePoints(StrokeTemplate.CHECK)))
        templates.add(createStroke(StrokeTemplate.CARET.name, getStrokePoints(StrokeTemplate.CARET)))
        templates.add(createStroke(StrokeTemplate.ZIGZAG.name, getStrokePoints(StrokeTemplate.ZIGZAG)))
        templates.add(createStroke(StrokeTemplate.ARROW.name, getStrokePoints(StrokeTemplate.ARROW)))
        templates.add(createStroke(StrokeTemplate.LEFT_SQUARE_BRACKET.name, getStrokePoints(StrokeTemplate.LEFT_SQUARE_BRACKET)))
        templates.add(createStroke(StrokeTemplate.RIGHT_SQUARE_BRACKET.name, getStrokePoints(StrokeTemplate.RIGHT_SQUARE_BRACKET)))
        templates.add(createStroke(StrokeTemplate.V.name, getStrokePoints(StrokeTemplate.V)))
        templates.add(createStroke(StrokeTemplate.DELETE.name, getStrokePoints(StrokeTemplate.DELETE)))
        templates.add(createStroke(StrokeTemplate.LEFT_CURLY_BRACE.name, getStrokePoints(StrokeTemplate.LEFT_CURLY_BRACE)))
        templates.add(createStroke(StrokeTemplate.RIGHT_CURLY_BRACE.name, getStrokePoints(StrokeTemplate.RIGHT_CURLY_BRACE)))
        templates.add(createStroke(StrokeTemplate.STAR.name, getStrokePoints(StrokeTemplate.STAR)))
        templates.add(createStroke(StrokeTemplate.PIGTAIL.name, getStrokePoints(StrokeTemplate.PIGTAIL)))
        return templates
    }
}