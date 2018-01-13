package com.example.android.quizapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class SecondQuestionAnswerTest {

	private final String ANSWER = MainActivity.answerKey[1];

	@Rule
	public ActivityTestRule<MainActivity> mActivityTestRule =
			new ActivityTestRule<>(MainActivity.class);

	@Test
	public void clickFirstOption_CheckCorrectAnswer() {
		onView(withId(R.id.start_button)).perform(click()); // Start the quiz
		onView(withId(R.id.radio_first)).perform(click()); // Check radio box
		onView(withId(R.id.submit_button)).perform(click()); // first question submit & move to second
		onView(withId(R.id.radio_first)).perform(click()); // Check radio box
		onView(withId(R.id.radio_first)).check(matches(isChecked())); // Ensure box is checked
		onView(withId(R.id.radio_first)).check(matches(not(withText(ANSWER)))); // wrong answer
	}

	@Test
	public void clickSecondOption_CheckCorrectAnswer() {
		onView(withId(R.id.start_button)).perform(click());
		onView(withId(R.id.radio_first)).perform(click());
		onView(withId(R.id.submit_button)).perform(click());
		onView(withId(R.id.radio_second)).perform(click());
		onView(withId(R.id.radio_second)).check(matches(isChecked()));
		onView(withId(R.id.radio_second)).check(matches(not(withText(ANSWER))));
	}

	@Test
	public void clickThirdOption_CheckCorrectAnswer() {
		onView(withId(R.id.start_button)).perform(click());
		onView(withId(R.id.radio_first)).perform(click());
		onView(withId(R.id.submit_button)).perform(click());
		onView(withId(R.id.radio_third)).perform(click());
		onView(withId(R.id.radio_third)).check(matches(isChecked()));
		onView(withId(R.id.radio_third)).check(matches(withText(ANSWER)));
	}

	@Test
	public void clickFourthOption_CheckCorrectAnswer() {
		onView(withId(R.id.start_button)).perform(click());
		onView(withId(R.id.radio_first)).perform(click());
		onView(withId(R.id.submit_button)).perform(click());
		onView(withId(R.id.radio_fourth)).perform(click());
		onView(withId(R.id.radio_fourth)).check(matches(isChecked()));
		onView(withId(R.id.radio_fourth)).check(matches(not(withText(ANSWER))));
	}
}
