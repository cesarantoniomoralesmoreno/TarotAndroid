package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.motherobject.HoroscopeModelObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest{
    @MockK  //(relaxed = true)//Esto me sirve para hacer falsas llamadas, y al enviarle esos parametros ya no me sale un error de que no se encuentra la respuesta
    private lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HoroscopeViewModel
    //Metodo para hacer cosas antes
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewModel is created then horoscopes are loaded`(){

        //Gives
        every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList
        viewModel = HoroscopeViewModel(horoscopeProvider)
        //When
        val horoscopes = viewModel.horoscope.value
        assertTrue(horoscopes.isNotEmpty())
        verify {  }
    }
}