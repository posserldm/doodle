package posserldm.doodle.tools

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import posserldm.doodle.databinding.ViewToolbarBinding

class ToolbarView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var binding: ViewToolbarBinding

    init {
        binding = ViewToolbarBinding.inflate(LayoutInflater.from(context), this)
        binding.btnToggle.setOnClickListener { toggleToolbar() }
        binding.toolbar.visibility = GONE
    }

    private fun toggleToolbar() {
        val height = binding.toolbar.height
        val animation = if (binding.toolbar.visibility == VISIBLE) {
            TranslateAnimation(0f, 0f, 0f, height.toFloat())
        } else {
            TranslateAnimation(0f, 0f, height.toFloat(), 0f)
        }
        animation.duration = 200
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                binding.toolbar.visibility = if (binding.toolbar.visibility == VISIBLE) GONE else VISIBLE
                binding.btnToggle.text = if (binding.toolbar.visibility == VISIBLE) "Hide Toolbar" else "Show Toolbar"
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.toolbar.startAnimation(animation)
    }
}
