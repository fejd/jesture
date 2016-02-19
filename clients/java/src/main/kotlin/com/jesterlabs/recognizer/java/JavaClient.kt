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

package com.jesterlabs.recognizer.java

import com.jesterlabs.jesture.recognizers.onedollar.OneDollarRecognizer
import com.jesterlabs.jesture.recognizers.common.data.Point
import java.awt.Canvas
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.*
import javax.swing.JFrame
import javax.swing.JScrollPane

fun main(args: Array<String>) {
    with (JFrame("Unistroke Recognizer")) {
        setSize(600, 600)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val canvas = StrokeCanvas()
        val scrollPane = JScrollPane(canvas)
        add(scrollPane)
        isVisible = true
        val mouseRecognizer = MouseRecognizer(canvas)
        canvas.addMouseListener(mouseRecognizer)
        canvas.addMouseMotionListener(mouseRecognizer)
    }
}

class StrokeCanvas() : Canvas() {

    var fromPoint = Point(0.0, 0.0)
    var toPoint = Point(0.0, 0.0)

    override fun paint(g: Graphics?) {
        g?.drawLine(fromPoint.x.toInt(), fromPoint.y.toInt(), toPoint.x.toInt(), toPoint.y.toInt())
    }

    override fun update(g: Graphics?) {
        paint(g)
    }

    fun clearCanvas() {
        graphics.clearRect(0, 0, width, height)
    }

    fun drawResult(result: String) {
        graphics.drawString(result, toPoint.x.toInt(), toPoint.y.toInt())
    }
}

class MouseRecognizer(val canvas : StrokeCanvas) : MouseAdapter() {

    val points = ArrayList<Point>()
    val dollarRecognizer = OneDollarRecognizer()

    override fun mousePressed(e: MouseEvent?) {
        super.mousePressed(e)
        points.clear()
        canvas.clearCanvas()
        points.add(Point(e!!.point.x.toDouble(), e!!.point.y.toDouble()))
    }

    override fun mouseReleased(e: MouseEvent?) {
        super.mouseReleased(e)
        canvas.fromPoint = points.last()
        points.add(Point(e!!.point.x.toDouble(), e!!.point.y.toDouble()))
        canvas.toPoint = points.last()
        canvas.repaint()
        val result = dollarRecognizer.recognize(points)
        canvas.drawResult(result.name)

    }

    override fun mouseDragged(e: MouseEvent?) {
        super.mouseDragged(e)
        canvas.fromPoint = points.last()
        points.add(Point(e!!.point.x.toDouble(), e!!.point.y.toDouble()))
        canvas.toPoint = points.last()
        canvas.repaint()
    }
}
