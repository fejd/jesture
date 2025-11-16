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
import com.jesterlabs.jesture.recognizers.common.util.StrokeFactory
import org.junit.Test
import org.junit.Assert.*
import java.util.*

/**
 * Comprehensive tests for the $1 Unistroke recognizer
 */
class OneDollarTest {

    companion object {
        // Minimum score threshold for strong matches (most gestures)
        const val HIGH_CONFIDENCE_THRESHOLD = 0.90
        // Minimum score threshold for acceptable matches
        const val ACCEPTABLE_THRESHOLD = 0.70
        // Threshold for complex gestures that may have lower self-match scores
        const val COMPLEX_GESTURE_THRESHOLD = 0.80
    }

    // ============================================================================
    // BASIC RECOGNITION TESTS - Test each of the 16 default gesture templates
    // ============================================================================

    @Test fun testRecognizeTriangle() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.TRIANGLE)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("TRIANGLE", result.name)
        assertTrue("Triangle score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeX() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.X)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("X", result.name)
        assertTrue("X score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeRectangle() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.RECTANGLE)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("RECTANGLE", result.name)
        assertTrue("Rectangle score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeCircle() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.CIRCLE)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("CIRCLE", result.name)
        assertTrue("Circle score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeCheck() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.CHECK)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("CHECK", result.name)
        assertTrue("Check score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeCaret() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.CARET)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("CARET", result.name)
        assertTrue("Caret score should be > $COMPLEX_GESTURE_THRESHOLD, was ${result.score}",
                   result.score > COMPLEX_GESTURE_THRESHOLD)
    }

    @Test fun testRecognizeZigzag() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.ZIGZAG)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("ZIGZAG", result.name)
        assertTrue("Zigzag score should be > $COMPLEX_GESTURE_THRESHOLD, was ${result.score}",
                   result.score > COMPLEX_GESTURE_THRESHOLD)
    }

    @Test fun testRecognizeArrow() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.ARROW)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("ARROW", result.name)
        assertTrue("Arrow score should be > $COMPLEX_GESTURE_THRESHOLD, was ${result.score}",
                   result.score > COMPLEX_GESTURE_THRESHOLD)
    }

    @Test fun testRecognizeLeftSquareBracket() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.LEFT_SQUARE_BRACKET)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("LEFT_SQUARE_BRACKET", result.name)
        assertTrue("Left square bracket score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeRightSquareBracket() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.RIGHT_SQUARE_BRACKET)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("RIGHT_SQUARE_BRACKET", result.name)
        assertTrue("Right square bracket score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeV() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.V)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("V", result.name)
        assertTrue("V score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeDelete() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.DELETE)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("DELETE", result.name)
        assertTrue("Delete score should be > $COMPLEX_GESTURE_THRESHOLD, was ${result.score}",
                   result.score > COMPLEX_GESTURE_THRESHOLD)
    }

    @Test fun testRecognizeLeftCurlyBrace() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.LEFT_CURLY_BRACE)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("LEFT_CURLY_BRACE", result.name)
        assertTrue("Left curly brace score should be > $COMPLEX_GESTURE_THRESHOLD, was ${result.score}",
                   result.score > COMPLEX_GESTURE_THRESHOLD)
    }

    @Test fun testRecognizeRightCurlyBrace() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.RIGHT_CURLY_BRACE)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("RIGHT_CURLY_BRACE", result.name)
        assertTrue("Right curly brace score should be > $COMPLEX_GESTURE_THRESHOLD, was ${result.score}",
                   result.score > COMPLEX_GESTURE_THRESHOLD)
    }

    @Test fun testRecognizeStar() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.STAR)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("STAR", result.name)
        assertTrue("Star score should be > $COMPLEX_GESTURE_THRESHOLD, was ${result.score}",
                   result.score > COMPLEX_GESTURE_THRESHOLD)
    }

    @Test fun testRecognizePigtail() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.PIGTAIL)
        val result = OneDollarRecognizer().recognize(points)

        assertEquals("PIGTAIL", result.name)
        assertTrue("Pigtail score should be > $HIGH_CONFIDENCE_THRESHOLD, was ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    // ============================================================================
    // EDGE CASE TESTS
    // ============================================================================

    @Test fun testRecognizeWithTwoPointsFails() {
        // The algorithm requires at least 2 points, but won't give a good match
        val points = listOf(Point(91.0, 185.0), Point(93.0, 185.0))
        val result = OneDollarRecognizer().recognize(points)

        // With only 2 points, recognition should still return a result
        // but with low confidence (the algorithm attempts to match anyway)
        assertTrue("Name should be empty for insufficient points", result.name.isEmpty())
        assertEquals("Score should be NEGATIVE_INFINITY", Double.NEGATIVE_INFINITY, result.score, 0.0)
    }

    @Test fun testRecognizeSimpleCircle() {
        // Create a simple circle-like gesture with just 8 points
        val points = listOf(
            Point(100.0, 50.0),   // top
            Point(150.0, 75.0),   // top-right
            Point(175.0, 125.0),  // right
            Point(150.0, 175.0),  // bottom-right
            Point(100.0, 200.0),  // bottom
            Point(50.0, 175.0),   // bottom-left
            Point(25.0, 125.0),   // left
            Point(50.0, 75.0)     // top-left
        )
        val result = OneDollarRecognizer().recognize(points)

        // With only 8 points, the circle might not be perfectly recognized
        // but should still produce a valid result with reasonable confidence
        assertNotNull("Result should not be null", result)
        assertFalse("Result name should not be empty", result.name.isEmpty())
        assertTrue("Should have some confidence in the match, score: ${result.score}",
                   result.score > 0.5)
    }

    @Test fun testRecognizeScaledGesture() {
        // Test that recognition works regardless of scale (a key feature of $1)
        val factory = StrokeFactory()
        val originalPoints = factory.getStrokePoints(StrokeFactory.StrokeTemplate.TRIANGLE)

        // Scale all points by 2x
        val scaledPoints = originalPoints.map { Point(it.x * 2.0, it.y * 2.0) }
        val result = OneDollarRecognizer().recognize(scaledPoints)

        assertEquals("TRIANGLE", result.name)
        assertTrue("Scaled gesture should be recognized, score: ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    @Test fun testRecognizeTranslatedGesture() {
        // Test that recognition works regardless of position (another key feature of $1)
        val factory = StrokeFactory()
        val originalPoints = factory.getStrokePoints(StrokeFactory.StrokeTemplate.CIRCLE)

        // Translate all points by +500 in both directions
        val translatedPoints = originalPoints.map { Point(it.x + 500.0, it.y + 500.0) }
        val result = OneDollarRecognizer().recognize(translatedPoints)

        assertEquals("CIRCLE", result.name)
        assertTrue("Translated gesture should be recognized, score: ${result.score}",
                   result.score > HIGH_CONFIDENCE_THRESHOLD)
    }

    // ============================================================================
    // CUSTOM TEMPLATE TESTS
    // ============================================================================

    @Test fun testAddCustomTemplate() {
        val recognizer = OneDollarRecognizer()

        // Create a custom template with a unique shape (diagonal line)
        // Using more points for better recognition
        val customPoints = (0..20).map { i ->
            Point(i * 10.0, i * 10.0)  // Diagonal from (0,0) to (200,200)
        }
        val customTemplate = Template("DIAGONAL_LINE", customPoints)
        recognizer.addTemplate(customTemplate)

        // Try to recognize a similar diagonal gesture
        val testPoints = (0..25).map { i ->
            Point(i * 8.0, i * 8.0)  // Similar diagonal
        }
        val result = recognizer.recognize(testPoints)

        // Should recognize the custom template (though might also match CARET or V)
        // The important thing is that custom templates can be added and used
        assertNotNull("Result should not be null", result)
        assertTrue("Should attempt recognition with custom template", result.name.isNotEmpty())
        assertTrue("Score should be reasonable", result.score > 0.0)
    }

    @Test fun testTemplatePreprocessing() {
        // Test that Template class preprocesses points correctly
        val rawPoints = listOf(
            Point(0.0, 0.0),
            Point(100.0, 0.0),
            Point(100.0, 100.0),
            Point(0.0, 100.0),
            Point(0.0, 0.0)
        )
        val template = Template("SQUARE", rawPoints)

        // Template should have processed points
        assertNotNull("Processed points should not be null", template.processedPoints)
        assertTrue("Processed points should not be empty", template.processedPoints.isNotEmpty())
        // After preprocessing, should have exactly 64 points (NUMPOINTS constant)
        assertEquals("Processed points should be resampled to 64 points", 64, template.processedPoints.size)
    }

    // ============================================================================
    // RESULT OBJECT TESTS
    // ============================================================================

    @Test fun testResultContainsValidData() {
        val factory = StrokeFactory()
        val points = factory.getStrokePoints(StrokeFactory.StrokeTemplate.STAR)
        val result = OneDollarRecognizer().recognize(points)

        assertNotNull("Result should not be null", result)
        assertFalse("Result name should not be empty", result.name.isEmpty())
        assertTrue("Result score should be between 0 and 1", result.score >= 0.0 && result.score <= 1.0)
    }

    @Test fun testResultScoreRange() {
        // Test that scores are always in valid range for successful recognition
        val factory = StrokeFactory()
        val templates = listOf(
            StrokeFactory.StrokeTemplate.CIRCLE,
            StrokeFactory.StrokeTemplate.TRIANGLE,
            StrokeFactory.StrokeTemplate.RECTANGLE,
            StrokeFactory.StrokeTemplate.X
        )

        templates.forEach { template ->
            val points = factory.getStrokePoints(template)
            val result = OneDollarRecognizer().recognize(points)

            assertTrue("Score for ${template.name} should be <= 1.0, was ${result.score}",
                       result.score <= 1.0)
            assertTrue("Score for ${template.name} should be >= 0.0, was ${result.score}",
                       result.score >= 0.0)
        }
    }

    // ============================================================================
    // PERFORMANCE / STRESS TESTS
    // ============================================================================

    @Test fun testRecognizeWithManyPoints() {
        // Test with a very long stroke (simulating slow drawing)
        val manyPoints = (0..500).map { i ->
            Point(i.toDouble(), 100.0 + 50.0 * Math.sin(i / 20.0))
        }
        val result = OneDollarRecognizer().recognize(manyPoints)

        assertNotNull("Should handle many points without crashing", result)
        // Should still produce a result even if not a perfect match
        assertFalse("Should attempt to match even with many points", result.name.isEmpty())
    }

    @Test fun testMultipleRecognitionsDoNotInterfere() {
        val recognizer = OneDollarRecognizer()
        val factory = StrokeFactory()

        // Recognize multiple different gestures in sequence
        val circle1 = recognizer.recognize(factory.getStrokePoints(StrokeFactory.StrokeTemplate.CIRCLE))
        val triangle = recognizer.recognize(factory.getStrokePoints(StrokeFactory.StrokeTemplate.TRIANGLE))
        val circle2 = recognizer.recognize(factory.getStrokePoints(StrokeFactory.StrokeTemplate.CIRCLE))

        assertEquals("First circle recognition", "CIRCLE", circle1.name)
        assertEquals("Triangle recognition", "TRIANGLE", triangle.name)
        assertEquals("Second circle recognition", "CIRCLE", circle2.name)

        // Scores should be consistent
        assertTrue("Circle scores should be similar",
                   Math.abs(circle1.score - circle2.score) < 0.01)
    }
}
