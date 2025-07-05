package com.example.lunchtray

import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.formatPrice
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class OrderViewModelTest {
    private val viewModel = OrderViewModel()

    @Test
    fun viewmodel_Initialization() {
        val uiState = viewModel.uiState.value

        assertEquals(null, uiState.entree)
        assertEquals(null, uiState.sideDish)
        assertEquals(null, uiState.accompaniment)
        assertEquals(0.0, uiState.itemTotalPrice, 0.0)
        assertEquals(0.0, uiState.orderTax, 0.0)
        assertEquals(0.0, uiState.orderTotalPrice, 0.0)
    }

    @Test
    fun viewmodel_verify_UpdateEntree() {
        viewModel.updateEntree(DataSource.entreeMenuItems[0])
        val uiState = viewModel.uiState.value
        val orderTax = DataSource.entreeMenuItems[0].price * 0.08
        val orderTotal = DataSource.entreeMenuItems[0].price + orderTax

        assertEquals(DataSource.entreeMenuItems[0], uiState.entree)
        assertEquals(DataSource.entreeMenuItems[0].price, uiState.itemTotalPrice, 0.0)
        assertEquals(orderTax, uiState.orderTax, 0.0)
        assertEquals(orderTotal, uiState.orderTotalPrice,0.0)
    }
    @Test
    fun viewmodel_verify_UpdateSideDish() {
        viewModel.updateSideDish(DataSource.sideDishMenuItems[0])
        val uiState = viewModel.uiState.value
        val orderTax = DataSource.sideDishMenuItems[0].price * 0.08
        val orderTotal = DataSource.sideDishMenuItems[0].price + orderTax

        assertEquals(DataSource.sideDishMenuItems[0], uiState.sideDish)
        assertEquals(DataSource.sideDishMenuItems[0].price, uiState.itemTotalPrice, 0.0)
        assertEquals(orderTax, uiState.orderTax, 0.0)
        assertEquals(orderTotal, uiState.orderTotalPrice,0.0)
    }
    @Test
    fun viewmodel_verify_UpdateAccompaniment() {
        viewModel.updateAccompaniment(DataSource.accompanimentMenuItems[0])
        val uiState = viewModel.uiState.value
        val orderTax = DataSource.accompanimentMenuItems[0].price * 0.08
        val orderTotal = DataSource.accompanimentMenuItems[0].price + orderTax

        assertEquals(DataSource.accompanimentMenuItems[0], uiState.accompaniment)
        assertEquals(DataSource.accompanimentMenuItems[0].price, uiState.itemTotalPrice, 0.0)
        assertEquals(orderTax, uiState.orderTax, 0.0)
        assertEquals(orderTotal, uiState.orderTotalPrice,0.0)
    }
    @Test
    fun viewmodel_verify_ResetOrder() {
        viewModel.updateEntree(DataSource.entreeMenuItems[0])
        viewModel.updateSideDish(DataSource.sideDishMenuItems[0])
        viewModel.updateAccompaniment(DataSource.accompanimentMenuItems[0])

        var uiState = viewModel.uiState.value

        assertTrue(null != uiState.entree)
        assertTrue(null != uiState.sideDish)
        assertTrue(null != uiState.accompaniment)
        assertTrue(0.0 < uiState.itemTotalPrice)
        assertTrue(0.0 < uiState.orderTax)
        assertTrue(0.0 < uiState.orderTotalPrice)

        viewModel.resetOrder()
        uiState = viewModel.uiState.value

        assertEquals(null, uiState.entree)
        assertEquals(null, uiState.sideDish)
        assertEquals(null, uiState.accompaniment)
        assertEquals(0.0, uiState.itemTotalPrice, 0.0)
        assertEquals(0.0, uiState.orderTax, 0.0)
        assertEquals(0.0, uiState.orderTotalPrice, 0.0)
    }
    @Test
    fun viewmodel_verify_FormatPriceOrder() {
        viewModel.updateEntree(DataSource.entreeMenuItems[0])

        val uiState = viewModel.uiState.value

        assertEquals(DataSource.entreeMenuItems[0].price.formatPrice(), uiState.entree?.price?.formatPrice())
    }
    @Test
    fun viewmodel_verify_FormatPriceItem() {
        viewModel.updateEntree(DataSource.entreeMenuItems[0])

        val uiState = viewModel.uiState.value

        assertEquals(DataSource.entreeMenuItems[0].getFormattedPrice(), uiState.entree?.price?.formatPrice())
    }
}