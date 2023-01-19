package com.example.newproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newproject.R
import com.example.newproject.databinding.FragmentRegionBinding
import com.example.newproject.ui.adapter.MainRegionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RegionFragment : Fragment() {

    private var _binding: FragmentRegionBinding? = null
    private val binding: FragmentRegionBinding
        get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private val mainAdapter = MainRegionAdapter {
        findNavController().navigate(
            R.id.action_regionFragment_to_areaFragment, bundleOf(
                "teritory" to it.territory,
                "name" to it.region
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val zone = it.getString("teritory", "")
            mainViewModel.getRegionList(zone)
            binding.toolbar.title = it.getString("name", "")
        } ?: kotlin.run {
            binding.toolbar.title = "Regions"
        }

        binding.regionRv.adapter = mainAdapter

        lifecycleScope.launchWhenCreated {
            mainViewModel.region.collectLatest {
                mainAdapter.setList(it.toMutableList())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}