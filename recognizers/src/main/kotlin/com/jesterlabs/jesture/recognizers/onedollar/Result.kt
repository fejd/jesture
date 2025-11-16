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

/**
 * Represents the result of a gesture recognition attempt.
 *
 * Contains both the name of the best-matching template and a confidence score
 * indicating how well the input gesture matched the template.
 *
 * ## Score Interpretation
 * The score is a normalized value between 0.0 and 1.0:
 * - **1.0**: Perfect match (identical gestures)
 * - **0.90-0.99**: Excellent match (highly confident recognition)
 * - **0.70-0.89**: Good match (reasonable confidence)
 * - **0.50-0.69**: Weak match (low confidence)
 * - **< 0.50**: Poor match (likely incorrect)
 *
 * Applications should establish threshold values based on their specific use case.
 * For example, a drawing app might require scores > 0.85, while a casual game
 * might accept scores > 0.70.
 *
 * ## Usage Example
 * ```kotlin
 * val result = recognizer.recognize(userPoints)
 *
 * if (result.score > 0.85) {
 *     println("Recognized ${result.name} with high confidence!")
 *     executeGesture(result.name)
 * } else {
 *     println("Uncertain recognition: ${result.name} (${result.score})")
 * }
 * ```
 *
 * @property name The name of the best-matching template (e.g., "CIRCLE", "TRIANGLE")
 * @property score The confidence score ranging from 0.0 (no match) to 1.0 (perfect match)
 * @see OneDollarRecognizer.recognize
 */
data class Result(val name: String, val score: Double)