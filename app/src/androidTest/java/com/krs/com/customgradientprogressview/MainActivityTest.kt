package com.krs.com.customgradientprogressview

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import android.support.test.rule.ActivityTestRule



class MainActivityTest {

    @get:Rule
    public val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)


    @Test
    fun shouldStartView() {
        val robot = MyRobot()
        robot.launch(mActivityRule)
    }

    @Test
    fun shouldShowCustomGradientView() {
        val robot = MyRobot()
        robot.launch(mActivityRule)
                .isViewDisplayed(R.id.custom_progress_view)
                //.isDescendentViewDisplayed(R.id.tvProgressStart, R.id.custom_progress_view)
                //.isDescendentViewDisplayed(R.id.tvProgressCurrent, R.id.custom_progress_view)
               //.isDescendentViewDisplayed(R.id.tvProgressEnd, R.id.custom_progress_view)



    }


}

class MyRobot {

    fun launch(mActivityRule: ActivityTestRule<MainActivity>): MyRobot {
        mActivityRule.launchActivity(Intent(InstrumentationRegistry.getTargetContext(), MainActivity::class.java))
        return this
    }

    fun isViewDisplayed(id: Int): MyRobot {
        onView(withId(id)).check(matches(isDisplayed()))
        return this
    }

    /*fun isDescendentViewDisplayed(id: Int, parentView: Int): MyRobot {
        onView(allOf(withId(id))).check(matches(isDisplayed()))

        *//* onView(allOf(withId(childid),
                 isDescendantOfA(allOf(withId(parentView))))).check(matches(isDisplayed()))*//*
        return this
    }*/

}
