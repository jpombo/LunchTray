package com.example.lunchtray.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.OrderUiState
import com.example.lunchtray.R
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.formatPrice
import org.junit.Rule
import org.junit.Test

class CheckoutScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    val orderUiState = OrderUiState(
        entree = DataSource.entreeMenuItems[0],
        sideDish = DataSource.sideDishMenuItems[0],
        accompaniment = DataSource.accompanimentMenuItems[0]
    )

    @Test
    fun checkoutScreen_verifyContent() {
        composeTestRule.setContent {
            CheckoutScreen(
                orderUiState = orderUiState,
                onCancelButtonClicked = {},
                onNextButtonClicked = {}
            )
        }
        val summary = composeTestRule.activity.getString(R.string.order_summary)
        val cancel = composeTestRule.activity.getString(R.string.cancel)
        val submit = composeTestRule.activity.getString(R.string.submit)

        composeTestRule.onNodeWithText(summary).assertIsDisplayed()
        composeTestRule.onNodeWithText(DataSource.entreeMenuItems[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(DataSource.entreeMenuItems[0].price.formatPrice())
        composeTestRule.onNodeWithText(DataSource.sideDishMenuItems[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(DataSource.sideDishMenuItems[0].price.formatPrice())
        composeTestRule.onNodeWithText(DataSource.accompanimentMenuItems[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(DataSource.accompanimentMenuItems[0].price.formatPrice())
        composeTestRule.onNodeWithTag(cancel)
            .assertIsDisplayed()
            .assertIsEnabled()
        composeTestRule.onNodeWithTag(submit)
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun checkout_verifyMath() {
        composeTestRule.setContent {
            CheckoutScreen(
                orderUiState = orderUiState,
                onCancelButtonClicked = {},
                onNextButtonClicked = {}
            )
        }
        val subtotal = composeTestRule.activity.getString(R.string.subtotal, orderUiState.itemTotalPrice.formatPrice())
        val tax = composeTestRule.activity.getString(R.string.tax, orderUiState.orderTax.formatPrice())
        val total = composeTestRule.activity.getString(R.string.total, orderUiState.orderTotalPrice.formatPrice())
        composeTestRule.onNodeWithText(subtotal).assertIsDisplayed()
        composeTestRule.onNodeWithText(tax).assertIsDisplayed()
        composeTestRule.onNodeWithText(total).assertIsDisplayed()
    }
}