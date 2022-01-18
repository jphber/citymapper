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
import com.jeanbernuy.citymapper.R
import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.data.DataSource
import com.jeanbernuy.citymapper.data.repository.StopPointDataRepository
import com.jeanbernuy.citymapper.databinding.FragmentLineDetailBinding
import com.jeanbernuy.citymapper.presentation.viewmodels.NearbyStationViewModel
import com.jeanbernuy.citymapper.presentation.viewmodels.VMFactory

/**
 * A simple [Fragment] subclass.
 * Use the [LineDetailFragment]
 * created by: Jean Bernuy
 */
class LineDetailFragment : Fragment() {

    private val viewModel by viewModels<NearbyStationViewModel> { VMFactory(StopPointDataRepository(DataSource())) }
    private var _binding: FragmentLineDetailBinding? = null
    private val binding get() = _binding!!
    private val args: LineDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLineDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAllValidRoutes(args.stationName,args.direction).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "${result.data}", Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), R.string.error_message, Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(requireContext(), R.string.try_again_message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}