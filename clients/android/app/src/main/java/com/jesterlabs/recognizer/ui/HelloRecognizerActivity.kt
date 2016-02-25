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

package com.jesterlabs.recognizer.ui

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewManager
import com.jesterlabs.jesture.recognizers.common.data.Point
import com.jesterlabs.jesture.recognizers.onedollar.OneDollarRecognizer
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.verticalLayout
import java.util.*

class HelloRecognizerActivity : Activity() {

    var recognizer = OneDollarRecognizer();
    val TAG = "HelloRecognizerActivity"
    var points: ArrayList<Point>

    init {
        points = ArrayList<Point>()
    }

    inline fun ViewManager.resultView() = resultView {}
    inline fun ViewManager.resultView(init: ResultView.() -> Unit) = ankoView({ ResultView(it) }, init)

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        val ID_TEXT_VIEW = 1
        verticalLayout {
            padding = dip(30)

            val resultView = resultView {
                id = ID_TEXT_VIEW
            }

            setOnTouchListener { resultText, motionEvent ->
                val resultView = findViewById(ID_TEXT_VIEW) as ResultView
                when(motionEvent.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        resultView.clearResults()
                        points.clear()
                        points.add(Point(motionEvent.x.toDouble(), motionEvent.y.toDouble()))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        points.add(Point(motionEvent.x.toDouble(), motionEvent.y.toDouble()))
                    }
                    MotionEvent.ACTION_UP -> {
                        points.add(Point(motionEvent.x.toDouble(), motionEvent.y.toDouble()))
                        val result = recognizer.recognize(points)
                        resultView.updateResult(result.name, result.score.toString())
                        Log.i(TAG, "Name: " + result.name)
                        Log.i(TAG, "Score: " + result.score)
                    }
                }
                resultView.updatePoints(points)
                resultView.invalidate()
                true
            }
        }
    }

    class ResultView(context: Context?) : View(context) {
        var points: ArrayList<Point>
        val paint: Paint
        var resultText: String = ""
        var resultScore: String = ""

        init {
            points = ArrayList<Point>()
            paint = Paint()
            paint.color = Color.GRAY
            paint.strokeWidth = 10f
            paint.textSize = 50f
        }

        override fun onDraw(canvas: Canvas) {
            points.forEachIndexed { i, point -> run {
                if (i < 1) {
                    return@forEachIndexed
                } else if (points.size < 2) {
                    return
                }
                val firstPoint = points.get(i-1)
                val secondPoint = points.get(i)
                canvas.drawLine(firstPoint.x.toFloat(), firstPoint.y.toFloat(),
                        secondPoint.x.toFloat(), secondPoint.y.toFloat(), paint)
            } }
            if (resultText.isNotEmpty()) {
                canvas.drawText(resultText, points.last().x.toFloat(), points.last().y.toFloat(),
                        paint)
            }
        }

        fun updatePoints(points: ArrayList<Point>) {
            this.points = points;
        }

        fun updateResult(resultText: String, resultScore: String) {
            this.resultText = resultText
            this.resultScore = resultScore
        }

        fun clearResults() {
            this.resultText = ""
            this.resultScore = ""
        }
    }
}
