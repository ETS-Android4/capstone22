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
public class OrderDrinkAndCustomizeTest {

    @Rule
    public ActivityTestRule<RegisterPageActivity> mActivityTestRule = new ActivityTestRule<>(RegisterPageActivity.class);

    @Test
    public void testOrderDrinkAndCustomizeTest() {
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
        appCompatEditText.perform(replaceText("Mary Jane"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("mj@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("mj123"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.confirmPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("mj123"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.CardNum),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("12341234123412"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.CardNum), withText("12341234123412"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.registerButton), withText("  Register  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                11),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.password), withText("mj123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("mj12345678"));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.password), withText("mj12345678"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.confirmPassword), withText("mj123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("mj12345678"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.confirmPassword), withText("mj12345678"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.confirmPassword), withText("mj12345678"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText11.perform(pressImeActionButton());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.registerButton), withText("  Register  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                11),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.orderPageButton), withContentDescription("Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.menuRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                3)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.drinkText), withText("Latte"),
                        withParent(allOf(withId(R.id.cvLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Latte")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.drinkDescription), withText("Espresso with milk"),
                        withParent(allOf(withId(R.id.cvLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Espresso with milk")));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.CustomizeButton), withText("Customize"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.addin_checkbox), withText("Whipped Cream"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.addin_flavor_view),
                                        0),
                                0),
                        isDisplayed()));
        materialCheckBox.perform(click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.addin_checkbox), withText("Caramel Drizzle"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.addin_flavor_view),
                                        0),
                                0),
                        isDisplayed()));
        materialCheckBox2.perform(click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.addin_checkbox), withText("Caramel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.addin_flavor_view),
                                        1),
                                0),
                        isDisplayed()));
        materialCheckBox3.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.customize_close_button), withText("Save"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.CustomizeButton), withText("Customize"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.customize_close_button), withText("Save"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.radioButton6), withText("Medium"),
                        childAtPosition(
                                allOf(withId(R.id.SizeRadioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                9)),
                                1),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction materialRadioButton2 = onView(
                allOf(withId(R.id.radioButton11), withText("Hot"),
                        childAtPosition(
                                allOf(withId(R.id.TypeRadioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                11)),
                                0),
                        isDisplayed()));
        materialRadioButton2.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.CountinueButton), withText("Add to Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.cart_item_name), withText("Medium Hot Latte"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText("Medium Hot Latte")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.cart_item_price), withText("$3.75"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView4.check(matches(withText("$3.75")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.cart_cost_text1), withText("$3.75"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView5.check(matches(withText("$3.75")));

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.backArrow5),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.menuRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                3)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction materialRadioButton3 = onView(
                allOf(withId(R.id.radioButton9), withText("Small"),
                        childAtPosition(
                                allOf(withId(R.id.SizeRadioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                9)),
                                0),
                        isDisplayed()));
        materialRadioButton3.perform(click());

        ViewInteraction materialRadioButton4 = onView(
                allOf(withId(R.id.radioButton10), withText("Iced"),
                        childAtPosition(
                                allOf(withId(R.id.TypeRadioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                11)),
                                1),
                        isDisplayed()));
        materialRadioButton4.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.CountinueButton), withText("Add to Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.cart_item_name), withText("Small Iced Coffee"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView6.check(matches(withText("Small Iced Coffee")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.cart_item_price), withText("$2.65"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView7.check(matches(withText("$2.65")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.cart_cost_text1), withText("$6.4"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView8.check(matches(withText("$6.4")));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.cart_remove_button), withText("X"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.cart_addin_text), withText("Caramel"),
                        withParent(withParent(withId(R.id.cart_item_recycler))),
                        isDisplayed()));
        textView9.check(matches(withText("Caramel")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.cart_addin_text), withText("Caramel Drizzle"),
                        withParent(withParent(withId(R.id.cart_item_recycler))),
                        isDisplayed()));
        textView10.check(matches(withText("Caramel Drizzle")));

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.finalplaceOrderButton), withText("  Place Order  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.finalplaceOrderButton), withText("  Place Order  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.menuRedBox3),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView11.check(matches(isDisplayed()));
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
