package com.jeanbernuy.citymapper.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeanbernuy.citymapper.R
import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.data.DataSource
import com.jeanbernuy.citymapper.data.repository.StopPointDataRepository
import com.jeanbernuy.citymapper.databinding.FragmentArrivalTimeBinding
import com.jeanbernuy.citymapper.presentation.ui.adapters.ArrivalTimesAdapter
import com.jeanbernuy.citymapper.presentation.viewmodels.NearbyStationViewModel
import com.jeanbernuy.citymapper.presentation.viewmodels.VMFactory

/**
 * A simple [Fragment] subclass.
 * Use the [ArrivalTimeFragment]
 * created by: Jean Bernuy
 */
class ArrivalTimeFragment : Fragment() {

    private val viewModel by viewModels<NearbyStationViewModel> {
        VMFactory(
            StopPointDataRepository(
                DataSource()
            )
        )
    }
    private val args: ArrivalTimeFragmentArgs by navArgs()

    private var _binding: FragmentArrivalTimeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArrivalTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        viewModel.fetchArrivalPredictions(args.naptanId).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvArrivalTimes.adapter =
                        ArrivalTimesAdapter(requireContext(), result.data)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.error_message, Toast.LENGTH_LONG)
                        .show()
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.try_again_message, Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun setupViews() {
        binding.rvArrivalTimes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArrivalTimes.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

}