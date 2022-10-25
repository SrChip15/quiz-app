package com.example.android.quizapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

	// Class instance variables for storing and grading the answers to the quiz questions
	private int head;
	private int numberOfCorrectAnswers;
	private ImageView hintImageForQuestion;
	private TextView questionText;
	private RadioGroup answerChoicesGroup;
	private Button submitButton;
	private EditText ninthQuestionInput;
	private String[] answerToTenthQuestion;

	public static final String[] answerKey = {
			"Little Whinging",
			"150",
			"Michael Corner",
			"Fountain of Magical Brethren",
			"Makes him wear the sorting hat and sets it on fire",
			"The Grey Lady",
			"Nymphadora Tonks",
			"Gellert Grindelwald",
			"Accio"
	};

	private final String[] userResponse = new String[9]; // ten answers incl multiple check box answer

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Setup globals
		hintImageForQuestion = findViewById(R.id.hint_image_view);
		questionText = findViewById(R.id.question_text_view);
		answerChoicesGroup = findViewById(R.id.radio_group);
		submitButton = findViewById(R.id.submit_button);
		ninthQuestionInput = findViewById(R.id.user_input);

		answerToTenthQuestion = new String[6];
	}

	/**
	 * This method is called when the start quiz button is clicked by an user
	 * @param v current view
	 */
	public void startQuiz(View v) {
		hideInitialScreen();

		// Set scroll view
		View scrollView = findViewById(R.id.scroll_view);
		scrollView.setVisibility(View.VISIBLE);

		// Set first question hint image
		hintImageForQuestion.setImageResource(R.drawable.question_one_hint_image);
		hintImageForQuestion.setVisibility(View.VISIBLE);

		// Display first question
		questionText.setText(R.string.question_1);
		questionText.setGravity(Gravity.NO_GRAVITY);
		questionText.setVisibility(View.VISIBLE);

		// Set multiple choices for first question
		Resources res = getResources();
		String[] getChoices = res.getStringArray(R.array.ChoicesQuestionOne);
		setRadioButtonText(getChoices);
		answerChoicesGroup.setVisibility(View.VISIBLE);

		// Set submit button
		submitButton.setText(R.string.submit);
		submitButton.setVisibility(View.VISIBLE);
	}

	/**
	 * Hides the initial screen
	 */
	private void hideInitialScreen() {
		// Remove banner image
		View bannerImageView = findViewById(R.id.banner_image);
		bannerImageView.setVisibility(View.GONE);

		// Remove banner text
		View bannerTextView = findViewById(R.id.banner_text);
		bannerTextView.setVisibility(View.GONE);

		// Remove start quiz button
		View startQuizButton = findViewById(R.id.start_button);
		startQuizButton.setVisibility(View.GONE);
	}

	/**
	 * This method is called when an user clicks the next button
	 * The button itself shows up only after the submit button is clicked
	 * @param v current view
	 */
	public void presentNextQuestion(View v) {
		Resources res = getResources();
		answerChoicesGroup.clearCheck();
		// Set screen corresponding to the next question - hint image, question,
		// choices & submit button
		switch (head) {
			case 1:
				hintImageForQuestion.setImageResource(R.drawable.question_two_hint_image);
				questionText.setText(R.string.question_2);
				String[] getChoicesQuestionTwo = res.getStringArray(R.array.ChoicesQuestionTwo);
				setRadioButtonText(getChoicesQuestionTwo);
				break;
			case 2:
				hintImageForQuestion.setScaleType(ImageView.ScaleType.FIT_CENTER);
				hintImageForQuestion.setImageResource(R.drawable.question_three_hint_image);
				questionText.setText(R.string.question_3);
				String[] getChoicesQuestionThree = res.getStringArray(R.array.ChoicesQuestionThree);
				setRadioButtonText(getChoicesQuestionThree);
				break;
			case 3:
				hintImageForQuestion.setScaleType(ImageView.ScaleType.CENTER_CROP);
				hintImageForQuestion.setImageResource(R.drawable.question_four_hint_image);
				questionText.setText(R.string.question_4);
				String[] getChoicesQuestionFour = res.getStringArray(R.array.ChoicesQuestionFour);
				setRadioButtonText(getChoicesQuestionFour);
				break;
			case 4:
				hintImageForQuestion.setImageResource(R.drawable.question_five_hint_image);
				questionText.setText(R.string.question_5);
				String[] getChoicesQuestionFive = res.getStringArray(R.array.ChoicesQuestionFive);
				setRadioButtonText(getChoicesQuestionFive);
				break;
			case 5:
				hintImageForQuestion.setImageResource(R.drawable.question_six_hint_image);
				questionText.setText(R.string.question_6);
				String[] getChoicesQuestionSix = res.getStringArray(R.array.ChoicesQuestionSix);
				setRadioButtonText(getChoicesQuestionSix);
				break;
			case 6:
				hintImageForQuestion.setImageResource(R.drawable.question_seven_hint_image);
				questionText.setText(R.string.question_7);
				String[] getChoicesQuestionSeven = res.getStringArray(R.array.ChoicesQuestionSeven);
				setRadioButtonText(getChoicesQuestionSeven);
				break;
			case 7:
				hintImageForQuestion.setImageResource(R.drawable.question_eight_hint_image);
				questionText.setText(R.string.question_8);
				String[] getChoicesQuestionEight = res.getStringArray(R.array.ChoicesQuestionEight);
				setRadioButtonText(getChoicesQuestionEight);
				break;
			case 8:
				// Edit Text question type. Remove RadioGroup view
				answerChoicesGroup.setVisibility(View.GONE);
				// Set the hint image
				hintImageForQuestion.setImageResource(R.drawable.question_nine_hint_image);
				// Set the text view for question
				questionText.setText(R.string.question_9);
				// Make EditTextView visible
				ninthQuestionInput.setVisibility(View.VISIBLE);
				break;
			default:
				// Question 10
				// Remove MCQ and EditText view
				ninthQuestionInput.setText("");
				ninthQuestionInput.setVisibility(View.GONE);

				// Make hint image visible and set hint image view
				hintImageForQuestion.setScaleType(ImageView.ScaleType.FIT_CENTER);
				hintImageForQuestion.setImageResource(R.drawable.question_ten_hint_image);

				// Set question 10 text to question TextView
				questionText.setText(R.string.question_10);

				// Make all CheckBox options visible
				isCheckboxVisible(v, true);
				break;
		}
	}

	/**
	 * This method is called when an user clicks the submit button to
	 * submits his/her answer to a question
	 * @param v current view
	 */
	public void submitAndNext(View v) {
		if (evaluateAnswer(v) == -1) return;
		head++;
		presentNextQuestion(v);
		if (head == 10) showSummary(v);
	}

	/**
	 * This method is called when an user wants to play again
	 * @param v current view
	 */
	public void onPlayAgainClick(View v) {
		// Remove play again button
		View getPlayAgainButton = findViewById(R.id.play_again_button);
		getPlayAgainButton.setVisibility(View.GONE);

		View resultsContainer = findViewById(R.id.quiz_results_container);
		resultsContainer.setVisibility(View.GONE);

		// Clear checkbox again
		clearCheckedBoxGroup(v, true);
		// Set Scale type to app default
		hintImageForQuestion.setScaleType(ImageView.ScaleType.CENTER_CROP);

		// Set global variables to default
		head = 0;
		numberOfCorrectAnswers = 0;
		cleanArray(userResponse);
		cleanArray(answerToTenthQuestion);

		// Present first question
		startQuiz(v);
	}

	/**
	 * This method is called when a multiple-choice question is presented to an user
	 *
	 * @param choices array that holds the multiple choices for a question
	 */
	private void setRadioButtonText(String[] choices) {
		// Set multiple choices
		for (int i = 0; i < answerChoicesGroup.getChildCount(); i++) {
			((RadioButton) answerChoicesGroup.getChildAt(i)).setText(choices[i]);
		}
	}

	/**
	 * This method is called when a toast has to be displayed to an user
	 *
	 * @param displayMessage text message for a toast view
	 */
	private void displayToastForResult(String displayMessage) {
		Toast eval = Toast.makeText(this, displayMessage, Toast.LENGTH_SHORT);
		eval.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 30);
		eval.show();
	}

	/**
	 * This method is called to track the CheckBox options that an user has checked as answers to
	 * the multiple CheckBox question type
	 * @param v current view
	 */
	@SuppressLint("NonConstantResourceId")
	public void onCheckboxClicked(View v) {
		// Is the view now checked?
		boolean checked = ((CheckBox) v).isChecked();

		// Check which checkbox was clicked
		switch (v.getId()) {
			case R.id.checkbox_option_1:
				if (checked) {
					answerToTenthQuestion[0] = ((CheckBox) v).getText().toString();
				} else {
					answerToTenthQuestion[0] = "";
				}
				break;
			case R.id.checkbox_option_2:
				if (checked) {
					answerToTenthQuestion[1] = ((CheckBox) v).getText().toString();
				} else {
					answerToTenthQuestion[1] = "";
				}
				break;
			case R.id.checkbox_option_3:
				if (checked) {
					answerToTenthQuestion[2] = ((CheckBox) v).getText().toString();
				} else {
					answerToTenthQuestion[2] = "";
				}
				break;
			case R.id.checkbox_option_4:
				if (checked) {
					answerToTenthQuestion[3] = ((CheckBox) v).getText().toString();
				} else {
					answerToTenthQuestion[3] = "";
				}
				break;
			case R.id.checkbox_option_5:
				if (checked) {
					answerToTenthQuestion[4] = ((CheckBox) v).getText().toString();
				} else {
					answerToTenthQuestion[4] = "";
				}
				break;
			case R.id.checkbox_option_6:
				if (checked) {
					answerToTenthQuestion[5] = ((CheckBox) v).getText().toString();
				} else {
					answerToTenthQuestion[5] = "";
				}
				break;
		}
	}

	/**
	 * This method is called to make the entire CheckBox cluster visible
	 *
	 * @param makeVisible boolean that indicates whether or not the CheckBox is to be made visible
	 */
	@SuppressWarnings("unused")
	private void isCheckboxVisible(View v, boolean makeVisible) {
		// Get individual CheckBox view from CheckBox cluster
		View checkBox1View = findViewById(R.id.checkbox_option_1);
		View checkBox2View = findViewById(R.id.checkbox_option_2);
		View checkBox3View = findViewById(R.id.checkbox_option_3);
		View checkBox4View = findViewById(R.id.checkbox_option_4);
		View checkBox5View = findViewById(R.id.checkbox_option_5);
		View checkBox6View = findViewById(R.id.checkbox_option_6);

		if (makeVisible) {
			// Make CheckBox view visible
			checkBox1View.setVisibility(View.VISIBLE);
			checkBox2View.setVisibility(View.VISIBLE);
			checkBox3View.setVisibility(View.VISIBLE);
			checkBox4View.setVisibility(View.VISIBLE);
			checkBox5View.setVisibility(View.VISIBLE);
			checkBox6View.setVisibility(View.VISIBLE);
		} else {
			// Remove CheckBox view
			checkBox1View.setVisibility(View.GONE);
			checkBox2View.setVisibility(View.GONE);
			checkBox3View.setVisibility(View.GONE);
			checkBox4View.setVisibility(View.GONE);
			checkBox5View.setVisibility(View.GONE);
			checkBox6View.setVisibility(View.GONE);
		}
	}

	/**
	 * This method is called to clear out the checked boxes for the CheckBox cluster
	 *
	 * @param isChecked a boolean indicating the checked status of a CheckBox view
	 */
	@SuppressWarnings({ "SameParameterValue", "unused", "ConstantConditions" })
	private void clearCheckedBoxGroup(View v, boolean isChecked) {
		CheckBox checkBox1 = findViewById(R.id.checkbox_option_1);
		CheckBox checkBox2 = findViewById(R.id.checkbox_option_2);
		CheckBox checkBox3 = findViewById(R.id.checkbox_option_3);
		CheckBox checkBox4 = findViewById(R.id.checkbox_option_4);
		CheckBox checkBox5 = findViewById(R.id.checkbox_option_5);
		CheckBox checkBox6 = findViewById(R.id.checkbox_option_6);

		if (isChecked) {
			checkBox1.setChecked(!isChecked);
			checkBox2.setChecked(!isChecked);
			checkBox3.setChecked(!isChecked);
			checkBox4.setChecked(!isChecked);
			checkBox5.setChecked(!isChecked);
			checkBox6.setChecked(!isChecked);
		}
	}

	/**
	 * Evaluate answers based on the type of question
	 * @param view current view
	 * @return 0 for successful evaluation and -1 for unsuccessful evaluation
	 */
	private int evaluateAnswer(View view) {
		// Check question type to appropriately evaluate answer
		if (answerChoicesGroup.getVisibility() == View.VISIBLE) {
			// Multiple-choice question is displayed to user
			// Get the ID of the checked RadioButton from RadioGroup view
			int checkedRadioButtonId = answerChoicesGroup.getCheckedRadioButtonId();
			RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);
			if (checkedRadioButtonId == -1) {
				// None of the radio buttons were checked
				displayToastForResult("Please check one of the options!");
				return -1;
			} else {
				// Get user answer from checked RadioButton
				userResponse[head] = checkedRadioButton.getText().toString();
				// Compare user answer with answerKey
				boolean isCorrect = userResponse[head].equals(answerKey[head]);
				if (isCorrect) {
					// Increment correct answers by 1
					numberOfCorrectAnswers++;
				}
				saveResultToResults(userResponse[head], isCorrect);
				return 0;
			}
		} else if (ninthQuestionInput.getVisibility() == View.VISIBLE) {
			// EditText question type is displayed to user
			userResponse[head] = ninthQuestionInput.getText().toString().toLowerCase();
			if (TextUtils.isEmpty(userResponse[head])) {
				displayToastForResult("Please enter text to proceed");
				return -1;
			}
			boolean isCorrect = userResponse[head].equals(answerKey[head].toLowerCase());
			if (isCorrect) {
				numberOfCorrectAnswers++;
			}
			saveResultToResults(userResponse[head], isCorrect);
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm != null) {
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			} else {
				Log.d("MainActivity", "Soft keyboard not present");
			}
			return 0;
		} else {
			// Multiple CheckBox question type is displayed to user
			int numberOfChoicesUnchecked = 0;
			for (String text : answerToTenthQuestion) {
				if (TextUtils.isEmpty(text)) numberOfChoicesUnchecked++;
			}
			if (numberOfChoicesUnchecked == 6) {
				displayToastForResult("Please check one or more of the options!");
				return -1;
			} else {
				StringBuilder ansCode = new StringBuilder();
				for (String userAnswer : answerToTenthQuestion) {
					if (userAnswer != null && !userAnswer.equals("")) {
						// Check only valid values in answer array
						if (userAnswer.equals(getString(R.string.checkbox_2))) {
							ansCode.append("c");
						} else if (userAnswer.equals(getString(R.string.checkbox_4)))
							ansCode.append("c");
						else if (userAnswer.equals(getString(R.string.checkbox_5)))
							ansCode.append("c");
						else ansCode.append("i");
					}
				}

				boolean isCorrectAnswer = ansCode.toString().equals("ccc");
				if (isCorrectAnswer) numberOfCorrectAnswers++;

				TextView answerTenthTextView = findViewById(R.id.answer_to_tenth_question);
				StringBuilder multiLineText = new StringBuilder();
				for (String checkBoxText : answerToTenthQuestion) {
					if (!TextUtils.isEmpty(checkBoxText)) {
						multiLineText.append(checkBoxText).append("\n");
					}
				}

				answerTenthTextView.setText(multiLineText);
				answerTenthTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);

				// Clear checked boxes when finish is clicked
				clearCheckedBoxGroup(view, true);
				return 0;
			}
		}
	}

	/**
	 * Aggregate evaluation results
	 * @param answerText text from chosen checkbox or radiobutton
	 * @param isCorrectAnswer boolean indicating if answer is correct or not
	 */
	private void saveResultToResults(String answerText, boolean isCorrectAnswer) {
		switch (head) {
			case 0:
				TextView answerOneTextView = findViewById(R.id.answer_to_first_question);
				answerOneTextView.setText(answerText);
				answerOneTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			case 1:
				TextView answerTwoTextView = findViewById(R.id.answer_to_second_question);
				answerTwoTextView.setText(answerText);
				answerTwoTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			case 2:
				TextView answerThreeTextView = findViewById(R.id.answer_to_third_question);
				answerThreeTextView.setText(answerText);
				answerThreeTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			case 3:
				TextView answerFourthTextView = findViewById(R.id.answer_to_fourth_question);
				answerFourthTextView.setText(answerText);
				answerFourthTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			case 4:
				TextView answerFifthTextView = findViewById(R.id.answer_to_fifth_question);
				answerFifthTextView.setText(answerText);
				answerFifthTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			case 5:
				TextView answerSixthTextView = findViewById(R.id.answer_to_sixth_question);
				answerSixthTextView.setText(answerText);
				answerSixthTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			case 6:
				TextView answerSeventhTextView = findViewById(R.id.answer_to_seventh_question);
				answerSeventhTextView.setText(answerText);
				answerSeventhTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			case 7:
				TextView answerEighthTextView = findViewById(R.id.answer_to_eighth_question);
				answerEighthTextView.setText(answerText);
				answerEighthTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			case 8:
				TextView answerNinthTextView = findViewById(R.id.answer_to_ninth_question);
				answerNinthTextView.setText(answerText);
				answerNinthTextView.setTextColor(isCorrectAnswer ? Color.GREEN : Color.RED);
				break;
			default:
				Log.e("MainActivity", "INVALID: Exceeds the number of questions");
				break;
		}
	}

	/**
	 * Display test performance summary
	 * @param view current view
	 */
	private void showSummary(View view) {
		// Hide submit
		submitButton.setVisibility(View.GONE);

		Button quizResultsButton = findViewById(R.id.show_quiz_results);
		quizResultsButton.setVisibility(View.VISIBLE);

		// Set summary image view
		hintImageForQuestion.setScaleType(ImageView.ScaleType.FIT_CENTER);
		hintImageForQuestion.setImageResource(R.drawable.summary_image_hogwarts_logo);

		// Set result text view
		TextView resultTextView = findViewById(R.id.question_text_view);
		int totalNumberOfQuestions = 10;
		float percentageOfCorrectAnswers =
				numberOfCorrectAnswers / (float) totalNumberOfQuestions;
		if (percentageOfCorrectAnswers < 0.75) {
			// Scores less than 75%
			resultTextView.setText(getString(
					R.string.average,
					numberOfCorrectAnswers,
					totalNumberOfQuestions
			));
		} else if (percentageOfCorrectAnswers < 1) {
			// Scores between 75% and 99%
			resultTextView.setText(getString(
					R.string.above_average,
					numberOfCorrectAnswers,
					totalNumberOfQuestions
			));
		} else {
			resultTextView.setText(R.string.perfect_score);
		}
		resultTextView.setGravity(Gravity.CENTER);
		resultTextView.setTypeface(Typeface.SANS_SERIF);

		// Remove multiple Checkboxes from view
		isCheckboxVisible(view, false);

		// Set play again button view
		View getPlayAgainButton = findViewById(R.id.play_again_button);
		getPlayAgainButton.setVisibility(View.VISIBLE);
	}

	/**
	 * Display quiz results with both the question and supplied answer
	 * Incorrect answer text is red in colour
	 * @param view current view
	 */
	public void showQuizResults(View view) {
		Button quizResultsButton = findViewById(R.id.show_quiz_results);
		quizResultsButton.setVisibility(View.GONE);

		hintImageForQuestion.setVisibility(View.GONE);

		TextView resultTextView = findViewById(R.id.question_text_view);
		resultTextView.setVisibility(View.GONE);

		View resultsContainer = findViewById(R.id.quiz_results_container);
		resultsContainer.setVisibility(View.VISIBLE);

	}

	/**
	 * Clear answer objects from previous state
	 * @param array answer object
	 */
	private void cleanArray(String[] array) {
		Arrays.fill(array, null);
	}
}
