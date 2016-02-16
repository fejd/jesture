package com.jesterlabs.recognizer.java

import com.jesterlabs.jesture.recognizers.onedollar.OneDollarRecognizer
import com.jesterlabs.jesture.recognizers.common.data.Point
import java.awt.Canvas
import java.awt.Color
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
