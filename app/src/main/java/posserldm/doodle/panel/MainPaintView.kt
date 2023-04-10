package posserldm.doodle.panel

import android.content.ContentValues
import android.content.Context
import android.graphics.*
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import java.io.File
import java.io.IOException
import kotlin.math.absoluteValue

class MainPaintView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // var isAllowDrawing = true

    private fun createPaint(): Paint {
        /**
         * color：设置画笔颜色。
         * strokeWidth：设置画笔的宽度。
         * style：设置画笔样式，有填充、描边、填充加描边三种样式。
         * strokeCap：设置线帽的样式，有圆形、方形和线条末端截止点三种样式。
         * strokeJoin：设置线段连接处的样式，有圆角、直角和斜角三种样式。
         * shader：设置填充样式，可以是颜色、线性渐变、径向渐变、位图等。
         * maskFilter：设置模糊效果，可以实现模糊、浮雕等效果。
         * pathEffect：设置路径效果，可以实现虚线、圆角、拐角等效果。
         * isAntiAlias 是 Paint 类的一个属性，它表示是否开启抗锯齿。
         * 抗锯齿可以使图形边缘更加平滑，避免出现锯齿状的走样现象，从而提高图形的清晰度和美观度
         */
        return Paint().apply {
            color = Color.BLACK
            isAntiAlias = false
            strokeWidth = 10f
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    }

    private val drawLines = mutableListOf(DrawLine(Path(), createPaint()).apply {
        drawActions.add(DrawAction(createPaint()))
    })

    // 导入的背景图片
    private var bgImage: Bitmap? = null
    private var bgImagePaint = Paint()


    override fun onTouchEvent(event: MotionEvent): Boolean {
        // if (isAllowDrawing) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawLines.last().drawActions.add(DrawAction(createPaint()))
                drawLines.last().ownerPath.moveTo(event.x, event.y)
                drawLines.last().drawActions.last().points.add(
                    DrawAction.DrawPoint(
                        event.x,
                        event.y,
                        DrawAction.MOVE_TO
                    )
                )
            }
            MotionEvent.ACTION_MOVE -> {
                drawLines.last().ownerPath.lineTo(event.x, event.y)
                drawLines.last().drawActions.last().points.add(
                    DrawAction.DrawPoint(
                        event.x,
                        event.y,
                        DrawAction.LINE_TO
                    )
                )
            }
            MotionEvent.ACTION_UP -> {
                // drawLines.last().drawActions.last().points.add(DrawAction.DrawPoint(event.x, event.y, DrawAction.LINE_TO))
            }
        }
        invalidate()
        // }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bgImage?.let {
            canvas.drawBitmap(it, 0f, 0f, bgImagePaint)
        }
        drawLines.forEach {
            canvas.drawPath(it.ownerPath, it.ownerPaint)
        }
    }

    /**
     * path是记录了用户手指绘制路径的变量，通过重置path可以清空用户绘制的图形。
     * invalidate()方法用于使当前视图无效，从而强制调用onDraw()方法进行重绘。
     * 在这里，调用invalidate()方法可以立即清除之前绘制的图形。
     */
    fun clear() {
        val tmpPaint = drawLines.last().ownerPaint
        drawLines.clear()
        drawLines.add(DrawLine(Path(), tmpPaint).apply {
            drawActions.add(DrawAction(tmpPaint))
        })
        bgImage = null
        invalidate()
    }

    fun reCall() {
        if (drawLines.isEmpty()) return
        if (drawLines.size == 1 && !drawLines.last().hasActions()) return
        // 当前path已经画有线
        if (drawLines.last().hasActions()) {
            Log.i("posserTest", "当前path已经画有线")
            drawLines.last().removeLastAction()
            drawLines.last().reDrawPath()
        } else {
            Log.i("posserTest", "当前path已经没有线")
            drawLines[drawLines.size - 2].drawActions.removeLast()
            drawLines[drawLines.size - 2].reDrawPath()
            if (!drawLines[drawLines.size - 2].hasActions()) {
                drawLines.removeAt(drawLines.size - 2)
            }
        }
        invalidate()
    }

    fun setBackgroundImage(bitmap: Bitmap) {
        /**
         * 为了避免背景的图片变形，只把比屏幕大的和如屏幕比例相近的缩放成屏幕大小
         */
        bgImage = if ((bitmap.width * 1.0 / bitmap.height - width * 1.0 / height).absoluteValue <= 0.1
            || (bitmap.width > width && bitmap.height > height)) {
            Bitmap.createScaledBitmap(bitmap, width, height, true)
        } else if (bitmap.width >= this.width) {
            Bitmap.createScaledBitmap(bitmap, width, bitmap.height, true)
        } else if (bitmap.height >= this.height) {
            Bitmap.createScaledBitmap(bitmap, bitmap.width, height, true)
        } else { bitmap }

    }


    fun setColor(color: String) {
        setColor(Color.parseColor(color))
    }

    fun setColor(color: Int) {
        Log.i("posserTest", "setColor")
        val p = createPaint()
        p.color = color
        p.strokeWidth = drawLines.last().ownerPaint.strokeWidth
        if (drawLines.last().drawActions.isEmpty()) {
            drawLines.removeLast()
        }
        drawLines.add(DrawLine(Path(), p).apply {
            drawActions.add(DrawAction(p))
        })
        invalidate()
    }

    fun setWidth(width: Float) {
        Log.i("poosserTest", "setWidth")
        val p = createPaint()
        p.color = drawLines.last().ownerPaint.color
        p.strokeWidth = width
        if (drawLines.last().drawActions.isEmpty()) {
            drawLines.removeLast()
        }
        drawLines.add(DrawLine(Path(), p).apply {
            drawActions.add(DrawAction(p))
        })
        invalidate()
    }

    private var beforeEraserPaintColor = Color.parseColor("#000000")
    fun openEraser() {
        beforeEraserPaintColor = drawLines.last().ownerPaint.color
        setColor("#FFFFFF")
    }

    fun closeEraser() {
        setColor(beforeEraserPaintColor)
    }

    fun saveImage() {
        val bitmap = getBitmap()
        saveImageToGallery(bitmap)
    }

    private fun getBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)
        layout(0, 0, width, height)
        draw(canvas)
        return bitmap
    }

    private fun saveImageToGallery(bitmap: Bitmap) {
        val fileName = "${System.currentTimeMillis()}.png"
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            /**
             * 这段代码的作用是为保存的图片指定一个相对路径，
             * 并且设置该图片的IS_PENDING属性为1，表示该图片正在被处理，
             * 尚未准备好。这是在Android Q（API Level 29）及以上版本中使用的新特性，
             * 即Scoped Storage（作用域存储）模式，该模式对应用程序访问存储设备上的文件进行了限制。
             * 在Scoped Storage模式下，应用程序只能在应用程序私有目录和应用程序创建的公共目录中访问文件，
             * 其他目录只能通过系统提供的API进行访问。因此，为了保存图片到公共目录中，需要指定一个相对路径，
             * 然后通过MediaStore API将图片插入到系统的MediaStore数据库中。插入成功后，需要将该图片的
             * IS_PENDING属性设置为0，表示该图片已经准备好，可以被其他应用程序访问。
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(
                    MediaStore.Images.Media.RELATIVE_PATH,
                    "${Environment.DIRECTORY_PICTURES}${File.separator}Doodle"
                )
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }

        }

        val resolver = context?.contentResolver
        val uri = resolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            try {
                val stream = resolver.openOutputStream(it)
                if (stream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.close()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                        resolver.update(uri, contentValues, null, null)
                    }
                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                Toast.makeText(context, "保存失败: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun diffPaint(p1: Paint, p2: Paint): Boolean {
        return p1.color != p2.color || p2.strokeWidth != p2.strokeWidth
    }

}
