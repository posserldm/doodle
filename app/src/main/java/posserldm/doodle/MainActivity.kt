package posserldm.doodle

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import posserldm.doodle.databinding.ActivityMainBinding
import posserldm.doodle.dialog.buildSaveImgAlterDialog
import posserldm.doodle.tools.fragment.PaintToolPanelFragment
import posserldm.doodle.tools.viewmodel.ColorSelectorVM
import posserldm.doodle.tools.viewmodel.WidthSelectorVM

private const val PERMISSION_REQUEST_CODE = 195
private const val REQUEST_PICK_IMAGE = 1


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val toolContentPanel: PaintToolPanelFragment by lazy {
        PaintToolPanelFragment()
    }

    // VMs
    private lateinit var colorSelectorVM: ColorSelectorVM
    private lateinit var widthSelectorVM: WidthSelectorVM

    // 相册
    private lateinit var imageLauncher: ActivityResultLauncher<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvents()
        initVMs()
        initToolViewPage()
        requestPermissions()
        imageLauncher = registerForImageActivity()
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

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return
        val progressions = arrayOf(
            Manifest.permission.ACCESS_MEDIA_LOCATION,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
        )
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, progressions, PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "请前往设置->应用->权限管理->打开存储权限，否则无法使用导入功能", Toast.LENGTH_LONG).show()
            }
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
        // 保存功能
        binding.toolNavSave.setOnClickListener {
            buildSaveImgAlterDialog(this, okCallback = {
                binding.mainContent.saveImage()
            }).show()
        }
        // 导入功能
        binding.toolNavIntroduce.setOnClickListener {
            imageLauncher.launch("image/*")
        }
    }

    private fun registerForImageActivity():ActivityResultLauncher<String> {
        return registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val bgImage = getBitmapFromUri(it)
                bgImage?.let { bitmap ->
                    binding.mainContent.setBackgroundImage(bitmap)
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        val inputStream = contentResolver.openInputStream(uri)
        inputStream?.let {
            return BitmapFactory.decodeStream(inputStream)
        }
        return null
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

