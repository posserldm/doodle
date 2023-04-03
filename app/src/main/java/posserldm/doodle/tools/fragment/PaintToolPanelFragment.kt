package posserldm.doodle.tools.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import posserldm.doodle.databinding.FragmentPaintToolPanelBinding
import posserldm.doodle.tools.adapter.PaintToolPanelAdapter
import posserldm.doodle.tools.viewmodel.ColorSelectorVM


class PaintToolPanelFragment : Fragment() {

    private var binding: FragmentPaintToolPanelBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaintToolPanelBinding.inflate(inflater, container, false)
        initViewPage()
        return binding?.root
    }

    private fun initViewPage() {
        binding?.let {
            val adapter = PaintToolPanelAdapter(this@PaintToolPanelFragment)
            it.paintToolViewPage.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}