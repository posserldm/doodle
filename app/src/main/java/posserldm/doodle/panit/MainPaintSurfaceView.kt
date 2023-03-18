package posserldm.doodle.panit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View

class MainPaintSurfaceView(context: Context, attr: AttributeSet): SurfaceView(context, attr),SurfaceHolder.Callback, View.OnTouchListener {

    private val surfaceHolder: SurfaceHolder = holder
    private val paint: Paint = Paint()
    private var canvas: Canvas? = null
    private var startX = 0.0F
    private var startY = 0.0F
    private var endX = 0.0F
    private var endY = 0.0F

    init {
        surfaceHolder.addCallback(this)
        setOnTouchListener(this)
        paint.color = Color.RED
        paint.strokeWidth = 5f

    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                endX = event.x
                endY = event.y
                drawLine(startX, startY, endX, endY)
                startX = endX
                startY = endY
            }
            MotionEvent.ACTION_UP -> {
                endX = event.x
                endY = event.y
                drawLine(startX, startY, endX, endY)
            }
        }
        return true
    }

    private fun drawLine(startX: Float, startY: Float, endX: Float, endY: Float) {
        Log.i("main", "draw line method be called!")
        canvas = surfaceHolder.lockCanvas()
        try {
            canvas?.drawLine(startX, startY, endX, endY, paint)
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {

    }

    override fun surfaceChanged(holder: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }
}