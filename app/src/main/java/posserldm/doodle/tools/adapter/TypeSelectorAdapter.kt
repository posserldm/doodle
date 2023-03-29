package posserldm.doodle.tools.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import posserldm.doodle.R

private val typeResIds = arrayListOf(
    "画笔", "铅笔", "毛笔", "记号笔", "水彩笔"
)

class TypeSelectorHolder(view: View): RecyclerView.ViewHolder(view) {
    val typeTitle: TextView = view.findViewById(R.id.paint_type_title)
}

class TypeSelectorAdapter: RecyclerView.Adapter<TypeSelectorHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeSelectorHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_selecot_item, parent, false)
        return TypeSelectorHolder(view)
    }

    override fun onBindViewHolder(holder: TypeSelectorHolder, position: Int) {
        holder.typeTitle.text = typeResIds[position]
    }

    override fun getItemCount(): Int = typeResIds.size
}