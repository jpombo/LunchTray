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
import com.example.lunchtray.ui.AccompanimentMenuScreen
import org.junit.Rule
import org.junit.Test

class AccompanimentMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun accompanimentScreen_verifyContent() {
        composeTestRule.setContent {
            AccompanimentMenuScreen(
                options = DataSource.accompanimentMenuItems,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = {}
            )
        }
        DataSource.accompanimentMenuItems.forEach {
            composeTestRule.onNodeWithText(it.name).assertIsDisplayed()
        }
    }
    @Test
    fun accompanimentScreen_verifyButtonsWithoutSelection() {
        composeTestRule.setContent {
            AccompanimentMenuScreen(
                options = DataSource.accompanimentMenuItems,
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
    fun accompanimentScreen_verifyButtonsWithSelection() {
        composeTestRule.setContent {
            AccompanimentMenuScreen(
                options = DataSource.accompanimentMenuItems,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = {}
            )
        }
        val cancelButton = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithTag(cancelButton)
            .assertIsDisplayed()
            .assertIsEnabled()

        composeTestRule.onNodeWithText(DataSource.accompanimentMenuItems[0].name).performClick()

        val nextButton = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithTag(nextButton)
            .assertIsDisplayed()
            .assertIsEnabled()
    }
}