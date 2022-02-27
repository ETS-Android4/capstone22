package com.example.carolina_coffee;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
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
public class bigTest1 {

    @Rule
    public ActivityTestRule<RegisterPageActivity> mActivityTestRule = new ActivityTestRule<>(RegisterPageActivity.class);

    @Test
    public void bigTest1() {
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

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("cdf7@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("passpass101"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.password), withText("passpass101"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.confirmPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("passpass101"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.confirmPassword), withText("passpass101"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText7.perform(pressImeActionButton());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.phone),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("9"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.phone), withText("(9"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("(9583"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.phone), withText("(9583"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.phone), withText("(958) 3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("(958) 3655"));

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.phone), withText("(958) 3655"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText12.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.phone), withText("(958) 365-5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText13.perform(replaceText("(958) 365-5555"));

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.phone), withText("(958) 365-5555"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText14.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.phone), withText("(958) 365-5555"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText15.perform(pressImeActionButton());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.registerButton), withText("  Register  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.inbox_icon),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.homePageButton), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.payPageButton), withContentDescription("Pay"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.orderPageButton), withContentDescription("Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.menuRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                3)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.radioButton7), withText("Large"),
                        childAtPosition(
                                allOf(withId(R.id.SizeRadioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                9)),
                                2),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction materialRadioButton2 = onView(
                allOf(withId(R.id.radioButton10), withText("Iced"),
                        childAtPosition(
                                allOf(withId(R.id.TypeRadioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                11)),
                                1),
                        isDisplayed()));
        materialRadioButton2.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.CountinueButton), withText("Add to Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.orderPageButton), withContentDescription("Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.menuRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                3)));
        recyclerView2.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction materialRadioButton3 = onView(
                allOf(withId(R.id.radioButton6), withText("Medium"),
                        childAtPosition(
                                allOf(withId(R.id.SizeRadioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                9)),
                                1),
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

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.CountinueButton), withText("Add to Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.finalplaceOrderButton), withText("  Place Order  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction bottomNavigationItemView5 = onView(
                allOf(withId(R.id.accountPageButton), withContentDescription("Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView5.perform(click());

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
                allOf(withId(R.id.backArrow5),
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

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.edit_cards_button), withText("Edit"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                11)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.delete_payment_1), withText("Delete"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                13)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction materialTextView4 = onView(
                allOf(withId(R.id.payment_method_box_1), withText("Payment Method 1"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        materialTextView4.perform(scrollTo(), click());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.fullName_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                5)));
        appCompatEditText16.perform(scrollTo(), replaceText("Chris"), closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.fullName_box_2), withText("Chris"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                5)));
        appCompatEditText17.perform(pressImeActionButton());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.card_number_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                7)));
        appCompatEditText18.perform(scrollTo(), replaceText("6465-6464-6555-5666"), closeSoftKeyboard());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.card_number_box_2), withText("6465-6464-6555-5666"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                7)));
        appCompatEditText19.perform(pressImeActionButton());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.exp_date_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText20.perform(scrollTo(), replaceText("64"), closeSoftKeyboard());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("64/"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText21.perform(scrollTo(), replaceText("64"));

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("64"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText22.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("6"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText23.perform(scrollTo(), replaceText("01"));

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText24.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01/"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText25.perform(scrollTo(), replaceText("01/12"));

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01/12"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText26.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01/12"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText27.perform(pressImeActionButton());

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.ccv_num_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                6)));
        appCompatEditText28.perform(scrollTo(), replaceText("655"), closeSoftKeyboard());

        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.ccv_num_box_2), withText("655"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                6)));
        appCompatEditText29.perform(pressImeActionButton());

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.billing_zip_box_2),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        appCompatEditText30.perform(scrollTo(), replaceText("64583"), closeSoftKeyboard());

        ViewInteraction appCompatEditText31 = onView(
                allOf(withId(R.id.billing_zip_box_2), withText("64583"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        appCompatEditText31.perform(pressImeActionButton());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.add_payment_method_button_2), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                4)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction appCompatEditText32 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01/12"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText32.perform(scrollTo(), replaceText("01/11"));

        ViewInteraction appCompatEditText33 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01/11"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText33.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText34 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01/11"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText34.perform(pressImeActionButton());

        ViewInteraction appCompatEditText35 = onView(
                allOf(withId(R.id.ccv_num_box_2), withText("655"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                6)));
        appCompatEditText35.perform(pressImeActionButton());

        ViewInteraction appCompatEditText36 = onView(
                allOf(withId(R.id.billing_zip_box_2), withText("64583"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        appCompatEditText36.perform(pressImeActionButton());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.add_payment_method_button_2), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                4)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction appCompatEditText37 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01/11"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText37.perform(scrollTo(), replaceText("01"));

        ViewInteraction appCompatEditText38 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("01"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText38.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText39 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText39.perform(scrollTo(), replaceText("11"));

        ViewInteraction appCompatEditText40 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText40.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText41 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11/"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText41.perform(scrollTo(), replaceText("11/11"));

        ViewInteraction appCompatEditText42 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11/11"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText42.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText43 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11/11"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText43.perform(pressImeActionButton());

        ViewInteraction appCompatEditText44 = onView(
                allOf(withId(R.id.ccv_num_box_2), withText("655"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                6)));
        appCompatEditText44.perform(pressImeActionButton());

        ViewInteraction appCompatEditText45 = onView(
                allOf(withId(R.id.billing_zip_box_2), withText("64583"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        appCompatEditText45.perform(pressImeActionButton());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.add_payment_method_button_2), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                4)));
        materialButton9.perform(scrollTo(), click());

        ViewInteraction appCompatEditText46 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11/11"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText46.perform(scrollTo(), replaceText("11/2"));

        ViewInteraction appCompatEditText47 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11/2"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText47.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText48 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11/2"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText48.perform(pressImeActionButton());

        ViewInteraction appCompatEditText49 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11/2"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8)));
        appCompatEditText49.perform(scrollTo(), replaceText("11/23"));

        ViewInteraction appCompatEditText50 = onView(
                allOf(withId(R.id.exp_date_box_2), withText("11/23"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                8),
                        isDisplayed()));
        appCompatEditText50.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText51 = onView(
                allOf(withId(R.id.ccv_num_box_2), withText("655"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                6)));
        appCompatEditText51.perform(pressImeActionButton());

        ViewInteraction appCompatEditText52 = onView(
                allOf(withId(R.id.billing_zip_box_2), withText("64583"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                2)));
        appCompatEditText52.perform(pressImeActionButton());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.add_payment_method_button_2), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                4)));
        materialButton10.perform(scrollTo(), click());

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.edit_cards_button), withText("Edit"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                11)));
        materialButton11.perform(scrollTo(), click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.delete_payment_2), withText("Delete"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                14)));
        materialButton12.perform(scrollTo(), click());

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.logOutButton), withText("Back"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout2),
                                        childAtPosition(
                                                withId(R.id.scroll_view2),
                                                0)),
                                10)));
        materialButton13.perform(scrollTo(), click());

        ViewInteraction materialButton14 = onView(
                allOf(withId(R.id.profile_button), withText("Profile"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout),
                                        childAtPosition(
                                                withId(R.id.scroll_view),
                                                0)),
                                0)));
        materialButton14.perform(scrollTo(), click());

        ViewInteraction materialButton15 = onView(
                allOf(withId(R.id.editInformationButton), withText("Edit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                15),
                        isDisplayed()));
        materialButton15.perform(click());

        ViewInteraction appCompatEditText53 = onView(
                allOf(withId(R.id.profileName), withText("Chris"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText53.perform(replaceText("Chris wynne"));

        ViewInteraction appCompatEditText54 = onView(
                allOf(withId(R.id.profileName), withText("Chris wynne"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText54.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText55 = onView(
                allOf(withId(R.id.profileName), withText("Chris wynne"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText55.perform(pressImeActionButton());

        pressBack();

        ViewInteraction materialButton16 = onView(
                allOf(withId(R.id.confirmChangesButton), withText("  Confirm  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        materialButton16.perform(click());

        ViewInteraction materialTextView5 = onView(
                allOf(withId(R.id.backArrow),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                13),
                        isDisplayed()));
        materialTextView5.perform(click());

        ViewInteraction materialButton17 = onView(
                allOf(withId(R.id.change_password_button), withText("Change Password"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout),
                                        childAtPosition(
                                                withId(R.id.scroll_view),
                                                0)),
                                1)));
        materialButton17.perform(scrollTo(), click());

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.custom),
                                childAtPosition(
                                        withId(R.id.customPanel),
                                        0)),
                        0),
                        isDisplayed()));
        editText.perform(replaceText("Password101"), closeSoftKeyboard());

        ViewInteraction materialButton18 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton18.perform(scrollTo(), click());

        ViewInteraction materialButton19 = onView(
                allOf(withId(R.id.customer_support_button), withText("Customer Support"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout),
                                        childAtPosition(
                                                withId(R.id.scroll_view),
                                                0)),
                                3)));
        materialButton19.perform(scrollTo(), click());

        ViewInteraction materialButton20 = onView(
                allOf(withId(R.id.red_back_button_2), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        materialButton20.perform(click());

        ViewInteraction materialButton21 = onView(
                allOf(withId(R.id.setting_button), withText("Settings"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout),
                                        childAtPosition(
                                                withId(R.id.scroll_view),
                                                0)),
                                2)));
        materialButton21.perform(scrollTo(), click());

        ViewInteraction materialButton22 = onView(
                allOf(withId(R.id.red_back_button_1), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                18),
                        isDisplayed()));
        materialButton22.perform(click());

        ViewInteraction materialButton23 = onView(
                allOf(withId(R.id.profile_button), withText("Profile"),
                        childAtPosition(
                                allOf(withId(R.id.cvLayout),
                                        childAtPosition(
                                                withId(R.id.scroll_view),
                                                0)),
                                0)));
        materialButton23.perform(scrollTo(), click());

        ViewInteraction materialButton24 = onView(
                allOf(withId(R.id.Deactivate_Account), withText("  Deactivate Account  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                17),
                        isDisplayed()));
        materialButton24.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction materialButton25 = onView(
                allOf(withId(android.R.id.button2), withText("CANCEL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton25.perform(scrollTo(), click());

        ViewInteraction materialButton26 = onView(
                allOf(withId(R.id.Deactivate_Account), withText("  Deactivate Account  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                17),
                        isDisplayed()));
        materialButton26.perform(click());

        ViewInteraction materialButton27 = onView(
                allOf(withId(android.R.id.button1), withText("YES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton27.perform(scrollTo(), click());

        ViewInteraction materialButton28 = onView(
                allOf(withId(R.id.Deactivate_Account), withText("  Deactivate Account  "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                17),
                        isDisplayed()));
        materialButton28.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction materialButton29 = onView(
                allOf(withId(android.R.id.button1), withText("YES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton29.perform(scrollTo(), click());

        pressBack();
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
