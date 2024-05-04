package com.ahmadMustafa.i210886

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.firebase.auth.FirebaseAuth
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestCase2 {

    private val countingIdlingResource = CountingIdlingResource("Authentication")

    private fun withItemCount(expectedCount: Int): ViewAssertion {
        return ViewAssertion { view, noViewFoundException ->
            if (view !is RecyclerView) {
                throw (noViewFoundException
                    ?: IllegalStateException("The view is not a RecyclerView"))
            }
            val adapter = view.adapter
            assertThat("RecyclerView item count", adapter?.itemCount, `is`(expectedCount))

        }
    }




    @Test
    fun loginTest() {
        ActivityScenario.launch(loginPage::class.java)
        Thread.sleep(1500)

        countingIdlingResource.increment();

        onView(withId(R.id.email))
            .perform(typeText("shahmustafa47@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.enterpass))
            .perform(typeText("asdf111"), closeSoftKeyboard())

        onView(allOf(withId(R.id.loginbutton), withText("Login"), isDisplayed())).perform(click())

        countingIdlingResource.decrement()
    }


    @Test
    fun myProfileNameTest() {
        Thread.sleep(5000)
        ActivityScenario.launch(myprofile::class.java)
        Thread.sleep(5000)
        onView(withId(R.id.textView91)).check(matches(withText("kingpin")))
    }


    @After
    fun tearDown() {
        FirebaseAuth.getInstance().signOut()
    }


}