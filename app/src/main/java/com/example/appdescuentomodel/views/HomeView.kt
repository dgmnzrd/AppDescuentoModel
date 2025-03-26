package com.example.appdescuentomodel.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appdescuentomodel.components.Alert
import com.example.appdescuentomodel.components.MainButton
import com.example.appdescuentomodel.components.MainTextField
import com.example.appdescuentomodel.components.SpaceH
import com.example.appdescuentomodel.components.TwoCards
import com.example.appdescuentomodel.viewModels.CalcularViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: CalcularViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "App Descuentos", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }) {
        ContentHomeView(it, viewModel)
    }
}

@Composable
fun ContentHomeView(paddingValues: PaddingValues, viewModel: CalcularViewModel) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModel.state

        TwoCards(
            title = "Precio",
            number1 = state.totalDescuento,
            title2 = "Descuento",
            number2 = state.precioDescuento
        )

        MainTextField(
            value = state.precio, onValueChange = {
                viewModel.onValue(it, "precio")
            }, label = "Precio")
        SpaceH()
        MainTextField(
            value = state.descuento, onValueChange = {
                viewModel.onValue(it, "descuento")
            }, label = "Descuento %")
        SpaceH()
        MainButton(text = "Generar Descuento") {
            viewModel.calcular()
        }
        SpaceH()
        MainButton(text = "Limpiar") {
            viewModel.limpiar()
        }

        if (state.showAlert) {
            Alert(
                title = "Error",
                message = "Debes ingresar un precio y un descuento",
                confirmText = "Aceptar",
                onConfirmClick = { viewModel.cancelAlert() }
            ) {

            }
        }
    }
}