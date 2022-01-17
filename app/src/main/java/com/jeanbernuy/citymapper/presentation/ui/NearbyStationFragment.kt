package com.jeanbernuy.citymapper.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.data.DataSource
import com.jeanbernuy.citymapper.data.repository.StopPointDataRepository
import com.jeanbernuy.citymapper.databinding.FragmentNearbyStationBinding
import com.jeanbernuy.citymapper.presentation.ui.adapters.StopPointAdapter
import com.jeanbernuy.citymapper.presentation.viewmodels.NearbyStationViewModel
import com.jeanbernuy.citymapper.presentation.viewmodels.VMFactory

/**
 * A simple [Fragment] subclass.
 * Use the [NearbyStationFragment]
 * created by: Jean Bernuy
 */
class NearbyStationFragment : Fragment() {

    private val viewModel by viewModels<NearbyStationViewModel> {
        VMFactory(
            StopPointDataRepository(
                DataSource()
            )
        )
    }

    private var _binding: FragmentNearbyStationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNearbyStationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        viewModel.fetchAllStopPoints.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvStopPoints.adapter =
                        StopPointAdapter(requireContext(), result.data.stopPoints)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Error on server...", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Error on server...", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupViews() {
        binding.rvStopPoints.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStopPoints.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}