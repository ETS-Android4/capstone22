package com.example.carolina_coffee;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FindDrinkFromMenuTest {

    @Rule
    public ActivityTestRule<RegisterPageActivity> mActivityTestRule = new ActivityTestRule<>(RegisterPageActivity.class);

    @Test
    public void findDrinkFromMenuTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.collapse_button),
                        childAtPosition(
                                withId(R.id.modal_root),
                                1),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.fullName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Apple Une"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("au123@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("au123456789"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.confirmPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("au123456789"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.confirmPassword), withText("au123456789"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.phone),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.CardNum),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("123456789"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.CardNum), withText("123456789"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText8.perform(pressImeActionButton());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.registerButton), withText("  Register  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                11),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.payPageButton), withContentDescription("Pay"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.orderPageButton), withContentDescription("Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.cart_item_name), withText("Coffee"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("Coffee")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.cart_item_name), withText("Americano"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView2.check(matches(withText("Americano")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.cart_item_name), withText("Latte"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText("Latte")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.menuRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                3)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.drinkText), withText("Americano"),
                        withParent(allOf(withId(R.id.cvLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView4.check(matches(withText("Americano")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.drinkDescription), withText("Espresso with water"),
                        withParent(allOf(withId(R.id.cvLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView5.check(matches(withText("Espresso with water")));
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
