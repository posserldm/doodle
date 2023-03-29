package posserldm.doodle.tools.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import posserldm.doodle.R
import posserldm.doodle.databinding.FragmentTypeSelectorBinding
import posserldm.doodle.tools.adapter.TypeSelectorAdapter


class TypeSelectorFragment : Fragment() {

    private var binding: FragmentTypeSelectorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTypeSelectorBinding.inflate(inflater, container, false)
        initRecycleView()
        return binding?.root
    }

    private fun initRecycleView() {
        binding?.let {
            it.typeSelectorRec.adapter = TypeSelectorAdapter()
            it.typeSelectorRec.layoutManager = LinearLayoutManager(this@TypeSelectorFragment.context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}