package posserldm.doodle.tools.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import posserldm.doodle.tools.fragment.ColorSelectorFragment
import posserldm.doodle.tools.fragment.TypeSelectorFragment
import posserldm.doodle.tools.fragment.WidthSelectorFragment

private const val PAINT_TOOL_PAGE_COUNT = 3

class PaintToolPanelAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = PAINT_TOOL_PAGE_COUNT

    override fun createFragment(position: Int): Fragment = getFragmentInstanceByPosition(position)

    private fun getFragmentInstanceByPosition(position: Int): Fragment = when (position) {
        0 -> ColorSelectorFragment()
        1 -> WidthSelectorFragment()
        2 -> TypeSelectorFragment()
        else -> throw RuntimeException("没有相应的工具页面可以展示")
    }

}