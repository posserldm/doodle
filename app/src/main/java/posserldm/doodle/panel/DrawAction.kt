package posserldm.doodle.panel

import android.graphics.Paint
import android.graphics.Path

class DrawLine (val ownerPath: Path, val ownerPaint: Paint) {
    val drawActions = mutableListOf<DrawAction>()

    fun reDrawPath() {
        ownerPath.reset()
        drawActions.forEach {
            it.drawPath(ownerPath)
        }
    }

    fun hasActions():Boolean {
        return drawActions.isNotEmpty() && drawActions.last().points.isNotEmpty()
    }

    fun removeLastAction() {
        if (drawActions.size == 1) {
            drawActions.clear()
            drawActions.add(DrawAction(ownerPaint))
            return
        }
        drawActions.removeLast()
    }
}

class DrawAction private constructor(
    val points: MutableList<DrawPoint>,
    private val width: Float,
    private val color: Int
) {

    companion object {
        const val MOVE_TO = 0
        const val LINE_TO = 1
    }

    constructor(paint: Paint):
            this(mutableListOf<DrawPoint>(), paint.strokeWidth, paint.color)

    fun drawPath(path: Path) {
        points.forEach {
            when (it.action) {
                MOVE_TO -> path.moveTo(it.x, it.y)
                LINE_TO -> path.lineTo(it.x, it.y)
                else -> throw RuntimeException("action type Error!")
            }
        }
    }

    data class DrawPoint(
        val x: Float,
        val y: Float,
        val action: Int
    )
}
