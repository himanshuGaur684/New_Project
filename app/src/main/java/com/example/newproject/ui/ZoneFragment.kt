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
import com.example.newproject.databinding.FragmentZoneBinding
import com.example.newproject.ui.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ZoneFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentZoneBinding? = null
    private val binding: FragmentZoneBinding get() = _binding!!

    private val adapter: MainAdapter = MainAdapter {
        findNavController().navigate(
            R.id.action_zoneFragment_to_regionFragment, bundleOf(
                "teritory" to it.territory,
                "name" to it.zone
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvZone.adapter = adapter

        arguments?.let {
            val zone = it.getString("teritory", "")
            mainViewModel.getZoneList(zone)
            binding.toolbar.title = it.getString("name", "")
        }

        lifecycleScope.launchWhenCreated {
            mainViewModel.zone.collectLatest {
                adapter.setList(it.toMutableList())
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZoneBinding.inflate(layoutInflater)
        return binding.root
    }


}