package com.example.horoscapp.ui.home.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.horoscapp.databinding.FragmentHoroscopeBinding


class HoroscopeFragment : Fragment() {

    //En los fragment los bindind son un poco distintos
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!//Cuando yo llame a binding el valor que devolvera no sera nulo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


}