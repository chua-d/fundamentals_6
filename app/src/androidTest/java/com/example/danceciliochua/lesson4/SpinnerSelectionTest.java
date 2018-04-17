package com.example.danceciliochua.lesson4;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SpinnerSelectionTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    public void iterateSpinnerItems() {
        String[] mArray = mActivityRule.getActivity().getResources().getStringArray(R.array.labels_array);
        int size = mArray.length;

        for(int i=0; i<size; i++) {
            onView(withId(R.id.label_spinner)).perform(click());
            onData(is(mArray[i])).perform(click());
            onView(withId(R.id.button_main)).perform(click());
            onView(withId(R.id.text_phonelabel)).check(matches(withText(containsString(mArray[i]))));

        }

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.danceciliochua.lesson4", appContext.getPackageName());
    }
}
