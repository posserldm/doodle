package posserldm.doodle.tools.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import posserldm.doodle.R

private val paintWidthValues = arrayListOf(
    1, 2, 4, 8, 10, 12, 15, 20, 22, 25, 30, 36, 50
)

class WidthSelectorHolder(view: View): RecyclerView.ViewHolder(view) {
    val widthItem: LinearLayout = view.findViewById(R.id.paint_width)
}

class WidthSelectorAdapter: RecyclerView.Adapter<WidthSelectorHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidthSelectorHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.width_selector_item, parent,false)
        return WidthSelectorHolder(view)
    }

    override fun onBindViewHolder(holder: WidthSelectorHolder, position: Int) {
        val params = holder.widthItem.layoutParams
        params.height = paintWidthValues[position]
    }

    override fun getItemCount(): Int = paintWidthValues.size
}