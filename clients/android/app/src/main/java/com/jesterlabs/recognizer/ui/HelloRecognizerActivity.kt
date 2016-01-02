package com.jesterlabs.recognizer.ui

import android.app.Activity
import android.os.Bundle
import com.fejd.unistroke.recognizers.onedollar.OneDollarRecognizer

class HelloRecognizerActivity : Activity() {

    var recognizer = OneDollarRecognizer();

    protected override fun onCreate(savedInstanceState : Bundle) {

    }
}