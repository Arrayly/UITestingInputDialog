package com.example.uitestinginputdialog

import android.view.InputDevice
import android.view.MotionEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Press.FINGER
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.Tap.SINGLE
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.*
import org.junit.runner.*

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_visibility_inputDialog() {
        onView(withId(R.id.MainActivity_btn)).perform(click())
        onView(withId(R.id.InputDialog_rootLayout))
            .check(matches(isDisplayed()))
        onView(withId(R.id.inputDialog_EditText))
            .check(matches(withHint(R.string.name)))
    }

    @Test
    fun test_inputDialog_isDismissed_CancelBtn() {
        onView(withId(R.id.MainActivity_btn)).perform(click())
//        onView(withId(R.id.inputDialog_Cancel_Btn)).perform(click())
//        pressBack()

        onView(withId(R.id.InputDialog_rootLayout)).perform(clickIn(200,200))
        onView(withId(R.id.InputDialog_rootLayout))
            .check(doesNotExist())
    }

    @Test
    fun test_inputDialog_textIsSet() {
        val name = "Bill"
        onView(withId(R.id.MainActivity_btn)).perform(click())
        onView(withId(R.id.inputDialog_EditText)).perform(ViewActions.typeText(name))
        onView(withId(R.id.inputDialog_Confirm_Btn)).perform(click())
        onView(withId(R.id.MainActivity_tv)).check(matches(withText(name)))
    }

    //https://stackoverflow.com/questions/52076779/kotlin-custom-dialog-in-android
    fun clickIn(x: Int, y: Int): ViewAction {
        return GeneralClickAction(
            SINGLE,
            CoordinatesProvider { view ->
                val screenPos = IntArray(2)
                view?.getLocationOnScreen(screenPos)

                val screenX = (screenPos[0] + x).toFloat()
                val screenY = (screenPos[1] + y).toFloat()

                floatArrayOf(screenX, screenY)
            },
            FINGER,
            InputDevice.SOURCE_MOUSE,
            MotionEvent.BUTTON_PRIMARY
        )
    }
}