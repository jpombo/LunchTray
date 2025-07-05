package com.example.lunchtray.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.SideDishMenuScreen
import org.junit.Rule
import org.junit.Test

class SideDishMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun sideDishScreen_verifyContent() {
        composeTestRule.setContent {
            SideDishMenuScreen(
                options = DataSource.sideDishMenuItems,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = {}
            )
        }
        DataSource.sideDishMenuItems.forEach {
            composeTestRule.onNodeWithText(it.name).assertIsDisplayed()
        }
    }
    @Test
    fun sideDishScreen_verifyButtonsWithoutSelection() {
        composeTestRule.setContent {
            SideDishMenuScreen(
                options = DataSource.sideDishMenuItems,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = {}
            )
        }
        val cancelButton = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithTag(cancelButton)
            .assertIsDisplayed()
            .assertIsEnabled()

        val nextButton = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithTag(nextButton)
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }
    @Test
    fun sideDishScreen_verifyButtonsWithSelection() {
        composeTestRule.setContent {
            SideDishMenuScreen(
                options = DataSource.sideDishMenuItems,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = {}
            )
        }
        val cancelButton = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithTag(cancelButton)
            .assertIsDisplayed()
            .assertIsEnabled()

        composeTestRule.onNodeWithText(DataSource.sideDishMenuItems[0].name).performClick()

        val nextButton = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithTag(nextButton)
            .assertIsDisplayed()
            .assertIsEnabled()
    }
}