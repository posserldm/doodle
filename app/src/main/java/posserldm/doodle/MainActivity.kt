package posserldm.doodle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import posserldm.doodle.databinding.ActivityMainBinding
import posserldm.doodle.tools.fragment.PaintToolPanelFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val toolContentPanel: PaintToolPanelFragment by lazy {
        PaintToolPanelFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvents()
        initToolViewPage()
        // here are test code
    }

    private fun initEvents() {
        // 画笔工具面板开合事件
        binding.toolNavPaintTool.setOnClickListener {
            if (binding.toolContent.visibility == View.VISIBLE) {
                binding.mainContent.isAllowDrawing = true
                binding.toolContent.visibility = View.GONE
            } else {
                binding.mainContent.isAllowDrawing = false
                binding.toolContent.visibility = View.VISIBLE
            }
        }
    }

    private fun initToolViewPage() {
        supportFragmentManager.beginTransaction()
            .add(R.id.tool_content_panel, toolContentPanel, toolContentPanel::class.java.simpleName)
            .commit()
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

