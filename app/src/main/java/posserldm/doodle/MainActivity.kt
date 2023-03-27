package posserldm.doodle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import posserldm.doodle.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvents()

    }

    private fun initEvents() {
        // 画笔工具面板开合事件
        binding.toolNavPaintTool.setOnClickListener {
            if (binding.toolContent.visibility == View.VISIBLE) {
                binding.mainContent.isAllowDrawing = false
                binding.toolContent.visibility = View.GONE
            } else {
                binding.mainContent.isAllowDrawing = true
                binding.toolContent.visibility = View.VISIBLE
            }
        }
    }

    private fun setToolContentAnima(view: View) {
        val translateAnimation = if (view.visibility == View.VISIBLE) {
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f)
        } else {
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f)
        }
        translateAnimation.duration = 200
        view.animation = translateAnimation
    }


}

