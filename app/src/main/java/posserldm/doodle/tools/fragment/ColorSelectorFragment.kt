package posserldm.doodle.tools.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import posserldm.doodle.databinding.FragmentColorSelectorBinding
import posserldm.doodle.tools.adapter.ColorSelectorAdapter
import posserldm.doodle.tools.viewmodel.ColorSelectorVM

class ColorSelectorFragment : Fragment() {

    private var binding: FragmentColorSelectorBinding? = null
    private lateinit var colorVM: ColorSelectorVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColorSelectorBinding.inflate(inflater, container, false)
        initRecycleView()
        return binding?.root
    }

    private fun initRecycleView() {
        colorVM = ViewModelProvider(requireActivity())[ColorSelectorVM::class.java]
        binding?.let {
            val adapter = ColorSelectorAdapter()
            adapter.setOnColorSelectListener { color ->
                colorVM.updateValue(color)
            }
            it.colorRecView.adapter = adapter
            it.colorRecView.layoutManager = GridLayoutManager(this@ColorSelectorFragment.context, 5)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}