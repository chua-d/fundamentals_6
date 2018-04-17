package com.example.danceciliochua.lesson4;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recyclerViewTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerview),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(15, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recyclerview),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)));
        recyclerView2.perform(actionOnItemAtPosition(15, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.word), withText("Clicked! Clicked! Word 15"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerview),
                                        12),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Clicked! Clicked! Word 15")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
