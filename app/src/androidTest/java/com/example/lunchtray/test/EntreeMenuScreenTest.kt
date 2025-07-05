package com.example.lunchtray.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.R
import org.junit.Rule
import org.junit.Test

class EntreeMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun entreeScreen_verifyContent() {
        composeTestRule.setContent {
            EntreeMenuScreen(
                DataSource.entreeMenuItems,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = {}
            )
        }
        DataSource.entreeMenuItems.forEach {
            composeTestRule.onNodeWithText(it.name).assertIsDisplayed()
        }
    }
    @Test
    fun entreeScreen_verifyButtonsWithoutSelection() {
        composeTestRule.setContent {
            EntreeMenuScreen(
                DataSource.entreeMenuItems,
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
    fun entreeScreen_verifyButtonsWithSelection() {
        composeTestRule.setContent {
            EntreeMenuScreen(
                DataSource.entreeMenuItems,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = {}
            )
        }
        val cancelButton = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithTag(cancelButton)
            .assertIsDisplayed()
            .assertIsEnabled()

        composeTestRule.onNodeWithText(DataSource.entreeMenuItems[0].name).performClick()

        val nextButton = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithTag(nextButton)
            .assertIsDisplayed()
            .assertIsEnabled()
    }
}