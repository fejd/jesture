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
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import com.jesterlabs.jesture.recognizers.common.data.Point
import com.jesterlabs.jesture.recognizers.onedollar.OneDollarRecognizer
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import java.util.*

class HelloRecognizerActivity : Activity() {

    var recognizer = OneDollarRecognizer();
    val TAG = "HelloRecognizerActivity"
    var points: ArrayList<Point>

    init {
        points = ArrayList<Point>()
    }

    protected override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        val ID_TEXT_VIEW = 1
        verticalLayout {
            padding = dip(30)

            val resultText = textView {
                textSize = 24f
                id = ID_TEXT_VIEW
            }

            setOnTouchListener { resultText, motionEvent ->
                when(motionEvent.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        points.clear()
                        points.add(Point(motionEvent.x.toDouble(), motionEvent.y.toDouble()))
                    }
                    MotionEvent.ACTION_MOVE -> points.add(Point(motionEvent.x.toDouble(), motionEvent.y.toDouble()))
                    MotionEvent.ACTION_UP -> {
                        points.add(Point(motionEvent.x.toDouble(), motionEvent.y.toDouble()))
                        val result = recognizer.recognize(points)
                        Log.d(TAG, "Name: " + result.name)
                        Log.d(TAG, "Score: " + result.score)

                        val textView = findViewById(ID_TEXT_VIEW) as TextView
                        textView.text = result.name
                    }
                }
                true
            }
        }
    }
}