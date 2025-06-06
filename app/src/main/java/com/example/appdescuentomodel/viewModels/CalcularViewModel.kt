package com.example.appdescuentomodel.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appdescuentomodel.model.CalcularState

class CalcularViewModel: ViewModel() {
    var state by mutableStateOf(CalcularState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "precio" -> state = state.copy(precio = value)
            "descuento" -> state = state.copy(descuento = value)
        }
    }

    private fun calcularPrecio(precio: Double, descuento: Double): Double {
        val res = precio * (1 - descuento / 100)
        return kotlin.math.round(res * 100) / 100
    }

    private fun calcularDescuento(precio: Double, descuento: Double): Double {
        val res = precio * (1 - descuento / 100)
        return kotlin.math.round(res * 100) / 100
    }

    fun calcular() {
        val precio = state.precio
        val descuento = state.descuento
        state = if (precio != "" && descuento != "") {
            state.copy(
                precioDescuento = calcularPrecio(precio.toDouble(), descuento.toDouble()),
                totalDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
            )
        } else {
            state.copy(
                showAlert = true
            )
        }
    }

    fun limpiar() {
        state = state.copy(
            precio = "",
            descuento = "",
            precioDescuento = 0.0,
            totalDescuento = 0.0
        )
    }

    fun cancelAlert() {
        state = state.copy(
            showAlert = false
        )
    }
}