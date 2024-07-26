package com.example.flappybird

import android.graphics.Bitmap
import android.graphics.Canvas

class Bird(private val bitmap: Bitmap, private var x: Int, private var y: Int) {
    private var velocity = 0
    private val gravity = 1

    fun update() {
        velocity += gravity
        y += velocity
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, x.toFloat(), y.toFloat(), null)
    }

    fun onTouch() {
        velocity = -20
    }
}
