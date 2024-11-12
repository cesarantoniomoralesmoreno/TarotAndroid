package com.example.horoscapp.data.network.response



import com.example.horoscapp.motherobject.HoroscopeModelObject
import com.example.horoscapp.motherobject.HoroscopeModelObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Assert.*
import org.junit.Test


class PredictionResponseTest{
    @Test
    fun `toDomain Should Return Correct Prediction Model`(){//Con las comillas podemos escribir una funcion con espacios.
        //Given
        val horoscopeResponse = anyResponse
        //When
        val predictionModel = horoscopeResponse.toDomain()
        //Then
        predictionModel.sign shouldBe  horoscopeResponse.sign
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope
    }
}