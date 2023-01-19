package com.example.newproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newproject.R
import com.example.newproject.databinding.FragmentAreaBinding
import com.example.newproject.ui.adapter.MainAreaAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AreaFragment : Fragment() {

    private var _binding: FragmentAreaBinding? = null
    private val binding: FragmentAreaBinding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private val mainAdapter = MainAreaAdapter {
        findNavController().navigate(R.id.action_areaFragment_to_searchFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val region = it.getString("teritory", "")
            mainViewModel.getAreaList(region)
            binding.toolbar.title = it.getString("name", "")
        } ?: kotlin.run { binding.toolbar.title = "Region Area" }

        binding.areaRv.adapter = mainAdapter

        lifecycleScope.launchWhenCreated {
            mainViewModel.area.collectLatest {
                mainAdapter.setList(it.toMutableList())
            }
        }
    }


}