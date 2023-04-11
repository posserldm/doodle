package posserldm.doodle.dialog

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import posserldm.doodle.R

class ShareDialogBuilder(
    private val context: Context
) {
    private val view: View
    private val qqShareBtn: ImageView
    private val qqZoneShareBtn: ImageView
    private val wxShareBtn: ImageView
    private val wxPyqShareBtn: ImageView
    private val dialog: Dialog
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.share_dialog_view, null, false)
        this.view = view
        qqShareBtn = view.findViewById(R.id.qq_share_btn)
        qqZoneShareBtn = view.findViewById(R.id.qq_zone_share_btn)
        wxShareBtn = view.findViewById(R.id.wx_share_btn)
        wxPyqShareBtn = view.findViewById(R.id.wx_pyq_share_btn)
        initEvents()
        dialog = buildDialog()
    }

    /**
     * 因为开发者权限申请失败，功能暂时没有实现
     */
    private fun initEvents() {
        // 分享到qq好友功能
        qqShareBtn.setOnClickListener {
            Log.i("posserTest", "分享到qq好友功能")
        }
        // 分享到qq空间功能
        qqZoneShareBtn.setOnClickListener {
            Log.i("posserTest", "分享到qq空间功能")
        }
        // 分享到微信好友功能
        wxShareBtn.setOnClickListener {
            Log.i("posserTest", "分享到微信好友功能")
        }
        // 分享到朋友圈功能
        wxPyqShareBtn.setOnClickListener {
            Log.i("posserTest", "分享到朋友圈功能")
        }

    }

    fun show() {
        dialog.show()
        // dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun buildDialog(): Dialog {
        // val builder =  AlertDialog.Builder(context)
        //     .setTitle("分享到")
        //     .setView(view)
        //     .setCancelable(true)
        // val dialog = builder.create()
        // dialog.window?.let {
        //     it.setGravity(Gravity.BOTTOM)
        //     val lp = WindowManager.LayoutParams()
        //     lp.copyFrom(it.attributes)
        //     lp.width = WindowManager.LayoutParams.MATCH_PARENT
        //     lp.height = 500
        //     it.attributes = lp
        // }
        val dialog = Dialog(context)
        dialog.setContentView(view)
        dialog.window?.let {
            // 设置弹出方向
            it.setGravity(Gravity.BOTTOM)
            // 设置自定义view的大小
            it.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
            // it.attributes.height = 500
            // it.setBackgroundDrawableResource(android.R.color.transparent)
        }
        return dialog
    }
}