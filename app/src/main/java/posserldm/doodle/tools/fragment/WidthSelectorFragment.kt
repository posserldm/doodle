package posserldm.doodle.tools.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import posserldm.doodle.databinding.FragmentWidthSelectorBinding
import posserldm.doodle.tools.adapter.WidthSelectorAdapter
import posserldm.doodle.tools.viewmodel.WidthSelectorVM

class WidthSelectorFragment : Fragment() {

    private var binding: FragmentWidthSelectorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWidthSelectorBinding.inflate(inflater, container, false)
        initRecycleView()
        return binding?.root
    }

    private fun initRecycleView() {
        binding?.let {
            val adapter = WidthSelectorAdapter()
            val widthVM = ViewModelProvider(requireActivity())[WidthSelectorVM::class.java]
            adapter.setOnUpdateWidthListener { width ->
                widthVM.updateValue(width)
            }
            it.widthSelectorRec.adapter = adapter
            it.widthSelectorRec.layoutManager = LinearLayoutManager(this@WidthSelectorFragment.context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}