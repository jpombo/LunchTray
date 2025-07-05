package com.example.lunchtray.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.lunchtray.LunchTrayApp
import com.example.lunchtray.LunchTrayScreen
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchTrayNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController
    private val entree = DataSource.entreeMenuItems[0].name
    private val dish = DataSource.sideDishMenuItems[0].name
    private val accompaniment = DataSource.accompanimentMenuItems[0].name

    @Before
    fun setupLunchTrayNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                    navigatorProvider.addNavigator(ComposeNavigator())
            }
            LunchTrayApp(navController = navController)
        }
    }

    // Start Screen - tests
    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }
    @Test
    fun navHost_verifyNavigationBackButtonNotShowOnStartScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    //Choose Entree Screen - tests
    @Test
    fun navHost_navigateToEntreeScreen() {
        navigateToEntreeScreen()
        navController.assertCurrentRouteName(LunchTrayScreen.Entree.name)
    }
    @Test
    fun navHost_verifyNavigationBackButtonShowOnEntreeScreen() {
        navigateToEntreeScreen()
        val backTest = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backTest).assertExists()
    }
    @Test
    fun navHost_navigationUpOnEntreeScreen() {
        navigateToEntreeScreen()
        navigateUp()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }
    @Test
    fun navHost_navigationCancelOnEntreeScreen() {
        navigateToEntreeScreen()
        navigateCancel()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    // Side Dish Screen - tests
    @Test
    fun navHost_navigateToSideDishScreen() {
        val entree = DataSource.entreeMenuItems[0].name
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navController.assertCurrentRouteName(LunchTrayScreen.SideDish.name)
    }
    @Test
    fun navHost_verifyNavigationBackButtonShowOnDishScreen() {
        val entree = DataSource.entreeMenuItems[0].name
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        val backTest = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backTest).assertExists()
    }
    @Test
    fun navHost_navigationUpOnDishScreen() {
        val entree = DataSource.entreeMenuItems[0].name
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateUp()
        navController.assertCurrentRouteName(LunchTrayScreen.Entree.name)
    }
    @Test
    fun navHost_navigationCancelOnDishScreen() {
        val entree = DataSource.entreeMenuItems[0].name
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateCancel()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    // Accompaniment Screen - tests
    @Test
    fun navHost_navigateToAccompanimentScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        navController.assertCurrentRouteName(LunchTrayScreen.Accompaniment.name)
    }
    @Test
    fun navHost_verifyNavigationBackButtonShowOnAccompanimentScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        val backTest = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backTest).assertExists()
    }
    @Test
    fun navHost_navigationUpOnAccompanimentScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        navigateUp()
        navController.assertCurrentRouteName(LunchTrayScreen.SideDish.name)
    }
    @Test
    fun navHost_navigationCancelOnAccompanimentScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        navigateCancel()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    // Order Screen - tests
    @Test
    fun navHost_navigateToOrderScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        navigateToNextScreen(accompaniment)
        navController.assertCurrentRouteName(LunchTrayScreen.Checkout.name)
    }
    @Test
    fun navHost_verifyNavigationBackButtonShowOnOrderScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        navigateToNextScreen(accompaniment)
        val backTest = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backTest).assertExists()
    }
    @Test
    fun navHost_navigationUpOnOrderScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        navigateToNextScreen(accompaniment)
        navigateUp()
        navController.assertCurrentRouteName(LunchTrayScreen.Accompaniment.name)
    }
    @Test
    fun navHost_navigationCancelOnOrderScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        navigateToNextScreen(accompaniment)
        navigateCancel()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }
    @Test
    fun navHost_verifyNavigationSubmitOnOrderScreen() {
        navigateToEntreeScreen()
        navigateToNextScreen(entree)
        navigateToNextScreen(dish)
        navigateToNextScreen(accompaniment)
        val submit = composeTestRule.activity.getString(R.string.submit)
        composeTestRule.onNodeWithTag(submit).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    // Support methods
    fun navigateToEntreeScreen() {
        composeTestRule.onNodeWithStringId(R.string.start_order)
            .performClick()
    }
    fun navigateToNextScreen(itemToClick: String) {
        val next = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithTag(itemToClick).performClick()
        composeTestRule.onNodeWithTag(next).performClick()
    }
    fun navigateUp() {
        val backTest = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backTest).performClick()
    }
    fun navigateCancel() {
        val cancelTest = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithTag(cancelTest).performClick()
    }
}