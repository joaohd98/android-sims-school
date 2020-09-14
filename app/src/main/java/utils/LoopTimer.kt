package utils

import android.os.Handler
import android.util.Log

class LoopTimer(
    private val limitTimer: Int,
    private var isPaused: () -> Boolean,
    private var onNotPaused: (Int) -> Unit,
    private var onFinished: () -> Unit
) {
    private var timerCount = limitTimer
    private var started = false
    private val delay = 10
    private val handler = Handler()

    init {
        startTimer()
    }

    private val runnableTimer = Runnable {
        Log.d("aaa", "runnableTimer")

        if(!isPaused()) {
            timerCount -= delay

            if(timerCount > 0) {
                onNotPaused((limitTimer / timerCount - 1) * 100)
            }
            else {
                onFinished()
            }
        }

        if(timerCount == 0) {
            stopTimer()
        }
        else if (started) {
            startTimer()
        }
    }

    private fun stopTimer() {
        Log.d("aaa", "stopTimer")

        started = false
        handler.removeCallbacks(runnableTimer)
    }

    private fun startTimer() {
        Log.d("aaa", "startTimer")

        started = true
        handler.postDelayed(runnableTimer, delay.toLong())
    }
}