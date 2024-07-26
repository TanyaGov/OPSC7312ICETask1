package com.example.flappybird

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val thread: GameThread
    private val bird: Bird
    private val pillars: MutableList<Pillar> = mutableListOf()

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
        isFocusable = true


        val birdBitmap = BitmapFactory.decodeResource(resources, R.drawable.bird)
        bird = Bird(birdBitmap, 100, height / 2)

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        thread.setRunning(false)
        while (retry) {
            try {
                thread.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        if (canvas != null) {

            bird.draw(canvas)

            for (pillar in pillars) {
                pillar.draw(canvas)
            }
        }
    }

    fun update() {
        bird.update()
        for (pillar in pillars) {
            pillar.update()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            bird.onTouch()
        }
        return true
    }
}

