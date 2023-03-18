package posserldm.doodle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import posserldm.doodle.databinding.ActivityMainBinding
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import android.widget.LinearLayout
import posserldm.doodle.panit.MainPaintSurfaceView

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var mainPaintSurfaceView: MainPaintSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        mainPaintSurfaceView = findViewById(R.id.main_paint)
    }
}
