package com.example.horoscapp.ui.luck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.horoscapp.databinding.FragmentLuckBinding
import com.example.horoscapp.ui.horoscope.HoroscopeViewModel
import com.example.horoscapp.ui.luck.LuckViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()//Para conectar el fragment al view model

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!//Cuando yo llame a binding el valor que devolvera no sera nulo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


}
