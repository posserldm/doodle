package posserldm.doodle.tools.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import posserldm.doodle.R

private val colorList = arrayListOf(
    "#000000", "#C0C0C0", "#808A87", "#708069", "#808069",
    "#00C957", "#802A2A", "#A39480", "#BDFCC9", "#308014",
    "#C76114", "#734A12", "#A020F0", "#5E2612", "#FF0000",
    "#9C661F", "#B0171F", "#0000FF", "#B03060", "#DAA569",
    "#3D59AB", "#872657", "#0B1746", "#03A89E", "#FFFF00",
    "#FF00FF", "#E3CF57", "#FF9912", "#EB8E55", "#FFE384"
)

class ColorSelectorViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val colorIconTV: TextView = view.findViewById(R.id.color_icon)
}

class ColorSelectorAdapter : RecyclerView.Adapter<ColorSelectorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorSelectorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.color_selector_item, parent, false)
        return ColorSelectorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorSelectorViewHolder, position: Int) {
        holder.colorIconTV.text = colorList[position]
        holder.colorIconTV.setBackgroundColor(Color.parseColor(colorList[position]))
    }

    override fun getItemCount(): Int = colorList.size
}