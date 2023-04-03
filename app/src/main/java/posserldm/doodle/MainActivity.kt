package posserldm.doodle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import posserldm.doodle.databinding.ActivityMainBinding
import posserldm.doodle.tools.fragment.PaintToolPanelFragment
import posserldm.doodle.tools.viewmodel.ColorSelectorVM
import posserldm.doodle.tools.viewmodel.WidthSelectorVM


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val toolContentPanel: PaintToolPanelFragment by lazy {
        PaintToolPanelFragment()
    }

    // VMs
    private lateinit var colorSelectorVM: ColorSelectorVM
    private lateinit var widthSelectorVM: WidthSelectorVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvents()
        initVMs()
        initToolViewPage()
        // here are test code
    }

    private fun initVMs() {
        colorSelectorVM = ViewModelProvider(this)[ColorSelectorVM::class.java]
        colorSelectorVM.color.observe(this) { newColor ->
            binding.mainContent.setColor(Color.parseColor(newColor))
        }
        widthSelectorVM = ViewModelProvider(this)[WidthSelectorVM::class.java]
        widthSelectorVM.width.observe(this) { newWidth ->
            binding.mainContent.setWidth(newWidth)
        }
    }

    private var isOpenEraser = false
    private fun initEvents() {
        // 画笔工具面板开合事件
        binding.toolNavPaintTool.setOnClickListener {
            if (binding.toolContent.visibility == View.VISIBLE) {
                // binding.mainContent.isAllowDrawing = true
                binding.toolNavPaintTool.setBackgroundResource(R.drawable.textview_border)
                binding.toolContent.visibility = View.GONE
            } else {
                // binding.mainContent.isAllowDrawing = false
                binding.toolNavPaintTool.setBackgroundResource(R.drawable.textview_selected_border)
                binding.toolContent.visibility = View.VISIBLE
            }
        }
        // 清除功能
        binding.toolNavClear.setOnClickListener {
            binding.mainContent.clear()
        }
        // 撤回功能
        binding.toolNavRecall.setOnClickListener {
            binding.mainContent.reCall()
        }
        // 橡皮功能
        binding.toolNavEraser.setOnClickListener {
            if (isOpenEraser) {
                binding.mainContent.closeEraser()
                binding.toolNavEraser.setBackgroundResource(R.drawable.textview_border)
            } else {
                binding.mainContent.openEraser()
                binding.toolNavEraser.setBackgroundResource(R.drawable.textview_selected_border)
            }
            isOpenEraser = !isOpenEraser
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
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f
            )
        } else {
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f
            )
        }
        translateAnimation.duration = 200
        view.animation = translateAnimation
    }


}

