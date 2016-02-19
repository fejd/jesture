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