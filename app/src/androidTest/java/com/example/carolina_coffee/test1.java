package com.example.carolina_coffee;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class test1 {

    @Rule
    public ActivityTestRule<RegisterPageActivity> mActivityTestRule = new ActivityTestRule<>(RegisterPageActivity.class);

    @Test
    public void test1() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.fullName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Chris"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.fullName), withText("Chris"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction button = onView(
                allOf(withId(R.id.collapse_button),
                        childAtPosition(
                                withId(R.id.modal_root),
                                1),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("dd@gmaill.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.email), withText("dd@gmaill.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("rrttyyuu"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.confirmPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("rrttyyuu"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.confirmPassword), withText("rrttyyuu"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText8.perform(pressImeActionButton());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.phone),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("9"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.phone), withText("(9"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("(9236"));

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.phone), withText("(9236"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText11.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.phone), withText("(923) 6"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("(923) 6686"));

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.phone), withText("(923) 6686"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText13.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.phone), withText("(923) 668-6"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText14.perform(replaceText("(923) 668-6686"));

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.phone), withText("(923) 668-6686"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText15.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.phone), withText("(923) 668-6686"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText16.perform(pressImeActionButton());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.CardNum),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText17.perform(pressImeActionButton());

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

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.accountPageButton), withContentDescription("Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.payment_method_box_1), withText("History"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        materialTextView.perform(scrollTo(), click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.backArrow2),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialTextView2.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(R.id.payment_method_box_2), withText("Payment\nPlans"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                3)));
        materialTextView3.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.logOutButton), withText("Back"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                10)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialTextView4 = onView(
                allOf(withId(R.id.payment_method_box_2), withText("Payment\nPlans"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                3)));
        materialTextView4.perform(scrollTo(), click());

        ViewInteraction materialTextView5 = onView(
                allOf(withId(R.id.payment_method_box_1), withText("Payment Method 1"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        materialTextView5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.fullName_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText18.perform(scrollTo(), replaceText("Cjejcjd"), closeSoftKeyboard());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.fullName_box_2), withText("Cjejcjd"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText19.perform(pressImeActionButton());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.fullName_box_2), withText("Cjejcjd"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText20.perform(scrollTo(), replaceText("Cjejcjdcjdjcjdjc"));

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.fullName_box_2), withText("Cjejcjdcjdjcjdjc"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText21.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.fullName_box_2), withText("Cjejcjdcjdjcjdjc"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText22.perform(pressImeActionButton());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.fullName_box_2), withText("Cjejcjdcjdjcjdjc"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText23.perform(scrollTo(), replaceText("Cjejcjdcjdjcjdjcjdjcjdjcjf"));

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.fullName_box_2), withText("Cjejcjdcjdjcjdjcjdjcjdjcjf"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText24.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.card_number_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                10)));
        appCompatEditText25.perform(scrollTo(), replaceText("6468 6468 6566 8646"), closeSoftKeyboard());

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.card_number_box_2), withText("6468 6468 6566 8646"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                10)));
        appCompatEditText26.perform(pressImeActionButton());

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.exp_date_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                11)));
        appCompatEditText27.perform(scrollTo(), replaceText("65/66"), closeSoftKeyboard());

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("65/66"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                11)));
        appCompatEditText28.perform(pressImeActionButton());

        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.ccv_num_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                9)));
        appCompatEditText29.perform(scrollTo(), replaceText("656"), closeSoftKeyboard());

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.ccv_num_box_2), withText("656"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                9)));
        appCompatEditText30.perform(pressImeActionButton());

        ViewInteraction appCompatEditText31 = onView(
                allOf(withId(R.id.billing_zip_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        appCompatEditText31.perform(scrollTo(), replaceText("64686"), closeSoftKeyboard());

        ViewInteraction appCompatEditText32 = onView(
                allOf(withId(R.id.billing_zip_box_2), withText("64686"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        appCompatEditText32.perform(pressImeActionButton());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.add_payment_method_button_2), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                7)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.edit_cards_button), withText("Edit"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                11)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.delete_payment_2), withText("Delete"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                14)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.logOutButton), withText("Back"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                10)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.logOutButton), withText("   Log Out   "),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                11)));
        materialButton7.perform(scrollTo(), click());
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
