package posserldm.doodle.tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import posserldm.doodle.R

data class ToolbarModel(
    val toolbarText: String
)

class ToolbarHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val toolTextView: TextView = itemView.findViewById(R.id.toolbar_item_text_view)
}

class ToolbarAdapter(
    private val itemList: MutableList<ToolbarModel>
) : RecyclerView.Adapter<ToolbarHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolbarHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.toolbar_item_view, parent, false)
        return ToolbarHolder(view)
    }

    override fun onBindViewHolder(holder: ToolbarHolder, position: Int) {
        holder.toolTextView.text = itemList[position].toolbarText
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}