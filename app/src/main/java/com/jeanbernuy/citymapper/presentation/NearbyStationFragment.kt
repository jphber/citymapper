package com.jeanbernuy.citymapper.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jeanbernuy.citymapper.databinding.FragmentNearbyStationBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NearbyStationFragment]
 * created by: Jean Bernuy
 */
class NearbyStationFragment : Fragment() {

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
}