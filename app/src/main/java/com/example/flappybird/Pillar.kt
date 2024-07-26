package com.example.flappybird

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

class Pillar(private var x: Int, private val width: Int, private val height: Int, private val gap: Int) {
    private val paint = Paint()

    fun update() {
        x -= 5 // Speed of pillar movement
    }

    fun draw(canvas: Canvas) {
        val topRect = Rect(x, 0, x + width, height)
        val bottomRect = Rect(x, height + gap, x + width, canvas.height)
        canvas.drawRect(topRect, paint)
        canvas.drawRect(bottomRect, paint)
    }
}
