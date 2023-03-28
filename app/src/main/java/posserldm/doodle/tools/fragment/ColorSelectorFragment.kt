package posserldm.doodle.tools.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import posserldm.doodle.databinding.FragmentColorSelectorBinding
import posserldm.doodle.tools.adapter.ColorSelectorAdapter

class ColorSelectorFragment : Fragment() {

    private var binding: FragmentColorSelectorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColorSelectorBinding.inflate(inflater, container, false)
        initRecycleView()
        return binding?.root
    }

    private fun initRecycleView() {
        binding?.let {
            it.colorRecView.adapter = ColorSelectorAdapter()
            it.colorRecView.layoutManager = GridLayoutManager(this@ColorSelectorFragment.context, 5)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}