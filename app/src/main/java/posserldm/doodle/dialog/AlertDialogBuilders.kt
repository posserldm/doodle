package posserldm.doodle.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun buildSaveImgAlterDialog(
    context: Context,
    okCallback: () -> Unit,
): AlertDialog.Builder {
    return buildAlterDialog(context, "保存作品", "您要保存您的作品吗？", false, okCallback = okCallback)
}

private fun buildAlterDialog(
    context: Context,
    title: String,
    msg: String,
    cancelAble: Boolean = false,
    okCallback: () -> Unit = {},
    cancelCallback: () -> Unit = {}
): AlertDialog.Builder {
    val builder = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(msg)
        .setCancelable(cancelAble)
    builder.setPositiveButton("确定") { _, _ ->
        okCallback()
    }

    builder.setNegativeButton("取消") { _, _ ->
        cancelCallback()
    }
    return builder
}