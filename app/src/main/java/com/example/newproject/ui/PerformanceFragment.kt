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
import com.example.newproject.databinding.FragmentPerformanceBinding
import com.example.newproject.ui.adapter.MainCountryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PerformanceFragment : Fragment() {

    private var _binding: FragmentPerformanceBinding? = null
    private val binding: FragmentPerformanceBinding
        get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private val adapter = MainCountryAdapter {
        findNavController().navigate(
            R.id.action_performanceFragment_to_zoneFragment,
            bundleOf("teritory" to it.territory,
            "name" to it.country)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerformanceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            countryRv.adapter = adapter
            lifecycleScope.launchWhenCreated {
                mainViewModel.country.collectLatest {
                    adapter.setList(it.toMutableList())
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}