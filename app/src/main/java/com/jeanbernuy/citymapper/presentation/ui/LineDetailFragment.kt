package com.jeanbernuy.citymapper.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jeanbernuy.citymapper.databinding.FragmentLineDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LineDetailFragment]
 * created by: Jean Bernuy
 */
class LineDetailFragment : Fragment() {

    private var _binding: FragmentLineDetailBinding? = null
    private val binding get() = _binding!!

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

}