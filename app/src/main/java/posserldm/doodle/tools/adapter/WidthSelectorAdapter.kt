package posserldm.doodle.tools.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import posserldm.doodle.R

private val paintWidthValues = arrayListOf(
    1, 2, 4, 8, 10, 12, 15, 20, 22, 25, 30, 36, 50
)

private const val SELECTED_ITEM_COLOR = "#BDFCC9"
private const val NOT_SELECT_ITEM_COLOR = "#C0C0C0"

class WidthSelectorHolder(view: View): RecyclerView.ViewHolder(view) {
    val widthItem: LinearLayout = view.findViewById(R.id.paint_width)
    val widthItemContent:LinearLayout = view.findViewById(R.id.paint_width_content)
}

class WidthSelectorAdapter: RecyclerView.Adapter<WidthSelectorHolder>() {

    private var onUpdateWidth: ((width: Float) -> Unit)? = null
    private var selectedItemPos = 4

    fun setOnUpdateWidthListener(onUpdateWidth: ((width: Float) -> Unit)) {
        this.onUpdateWidth = onUpdateWidth
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidthSelectorHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.width_selector_item, parent,false)
        return WidthSelectorHolder(view)
    }

    override fun onBindViewHolder(holder: WidthSelectorHolder, position: Int) {
        val params = holder.widthItem.layoutParams
        params.height = paintWidthValues[position]
        if (selectedItemPos == position) {
            holder.widthItemContent.setBackgroundColor(Color.parseColor(SELECTED_ITEM_COLOR))
        } else {
            holder.widthItemContent.setBackgroundColor(Color.parseColor(NOT_SELECT_ITEM_COLOR))
        }
        holder.widthItemContent.setOnClickListener {
            Log.i("posserTest", "width onBindViewHolder")
            onUpdateWidth?.let { callback -> callback(paintWidthValues[position].toFloat()) }
            holder.widthItemContent.setBackgroundColor(Color.parseColor(SELECTED_ITEM_COLOR))
            notifyItemChanged(selectedItemPos)
            selectedItemPos = holder.layoutPosition
        }
    }

    override fun getItemCount(): Int = paintWidthValues.size
}