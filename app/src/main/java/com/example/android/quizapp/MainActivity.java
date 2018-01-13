package com.example.android.quizapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

	/** Class instance variables for storing and grading the answers to the quiz questions */
	private int head;
	private int numberOfCorrectAnswers;
	private String trackAnswers = "";
	private ImageView hintImageForQuestion;
	private TextView questionText;
	private RadioGroup answerChoicesGroup;
	private Button submitButton;
	private Button nextButton;
	private EditText ninthQuestionInput;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Setup globals
		hintImageForQuestion = findViewById(R.id.hint_image_view);
		questionText = findViewById(R.id.question_text_view);
		answerChoicesGroup = findViewById(R.id.radio_group);
		submitButton = findViewById(R.id.submit_button);
		nextButton = findViewById(R.id.next_button);
		ninthQuestionInput = findViewById(R.id.user_input);
	}

	/** This method is called when the start quiz button is clicked by an user */
	public void startQuiz(View v) {
		// Remove banner image
		View bannerImageView = findViewById(R.id.banner_image);
		bannerImageView.setVisibility(View.GONE);

		// Remove banner text
		View bannerTextView = findViewById(R.id.banner_text);
		bannerTextView.setVisibility(View.GONE);

		// Remove start quiz button
		View startQuizButton = findViewById(R.id.start_button);
		startQuizButton.setVisibility(View.GONE);

		// Set scroll view
		View scrollView = findViewById(R.id.scroll_view);
		scrollView.setVisibility(View.VISIBLE);

		// Set first question hint image
		head++;
		hintImageForQuestion.setImageResource(R.drawable.question_one_hint_image);
		hintImageForQuestion.setVisibility(View.VISIBLE);

		// Make question TextView visible and Set first question
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
	 * This method is called when an user clicks the next button
	 * The button itself shows up only after the submit button is clicked
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
		/*else {
			// Remove MCQ  and EditText view
			ninthQuestionInput.setText("");
			ninthQuestionInput.setVisibility(View.GONE);

			// Make hint image visible and set hint image view
			hintImageForQuestion.setScaleType(ImageView.ScaleType.FIT_CENTER);
			hintImageForQuestion.setImageResource(R.drawable.question_ten_hint_image);

			// Set question 10 text to question TextView
			questionText.setText(R.string.question_10);

			// Make all CheckBox options visible
			isCheckboxVisible(v, true);

			// Set submit button text to finish
			submitButton.setText(R.string.finish);
		}

		// Make submit button visible
		submitButton.setVisibility(View.VISIBLE);

		// Remove next button
		presentNextQuestion.setVisibility(View.GONE);*/
	}

	/**
	 * This method is called when an user clicks the submit button to
	 * submits his/her answer to a question
	 */
	public void submitAndNext(View v) {
		evaluateAnswer(v);
		head++;
		presentNextQuestion(v);
	}

	/** This method is called when an user wants to play again */
	public void onPlayAgainClick(View v) {
		// Remove play again button
		View getPlayAgainButton = findViewById(R.id.play_again_button);
		getPlayAgainButton.setVisibility(View.GONE);

		// Clear checkbox again
		clearCheckedBoxGroup(v, true);
		// Set Scale type to app default
		hintImageForQuestion.setScaleType(ImageView.ScaleType.CENTER_CROP);

		// Set global variables to default
		head = 0;
		numberOfCorrectAnswers = 0;
		trackAnswers = "";

		// Present first question
		startQuiz(v);
	}

	/**
	 * This method is called when a multiple-choice question is presented to an user
	 *
	 * @param choices a String array that holds the multiple choices for a question
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
	 * @param displayMessage a String that sets the text for a toast view
	 */
	private void displayToastForResult(String displayMessage) {
		Toast eval = Toast.makeText(this, displayMessage, Toast.LENGTH_SHORT);
		eval.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 30);
		eval.show();
	}

	/**
	 * This method is called to track the CheckBox options that an user has checked as answers to
	 * the multiple CheckBox question type
	 */
	public void onCheckboxClicked(View v) {
		// Is the view now checked?
		boolean checked = ((CheckBox) v).isChecked();

		// Check which checkbox was clicked
		switch (v.getId()) {
			case R.id.checkbox_option_1:
				if (checked)
					// Add i for incorrect answer to trackAnswers String
					trackAnswers += "i";
				break;
			case R.id.checkbox_option_2:
				if (checked)
					// Add c for correct answer to trackAnswers String
					trackAnswers += "c";
				break;
			case R.id.checkbox_option_3:
				if (checked)
					// Add i for incorrect answer to trackAnswers String
					trackAnswers += "i";
				break;
			case R.id.checkbox_option_4:
				if (checked)
					// Add c for correct answer to trackAnswers String
					trackAnswers += "c";
				break;
			case R.id.checkbox_option_5:
				// Add c for correct answer to trackAnswers String
				trackAnswers += "c";
				break;
			case R.id.checkbox_option_6:
				if (checked)
					// Add i for incorrect answer to trackAnswers String
					trackAnswers += "i";
				break;
		}
	}

	/**
	 * This method is called to make the entire CheckBox cluster visible
	 *
	 * @param makeVisible a boolean that indicates whether or not the CheckBox is to be made visible
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

	private void evaluateAnswer(View view) {
		// Check question type to appropriately evaluate answer
		if (answerChoicesGroup.getVisibility() == View.VISIBLE) {
			// Multiple-choice question is displayed to user
			// Get the ID of the checked RadioButton from RadioGroup view
			int checkedRadioButtonId = answerChoicesGroup.getCheckedRadioButtonId();
			RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);
			if (checkedRadioButtonId == -1) {
				// None of the radio buttons were checked
				displayToastForResult("Please check one of the options!");
			} else {
				// Get user answer from checked RadioButton
				String userResponse = checkedRadioButton.getText().toString();
				// Compare user answer with answerKey
				boolean isCorrect = userResponse.equals(answerKey[head]);
				if (isCorrect) {
					// Increment correct answers by 1
					numberOfCorrectAnswers++;
				}
			}
		} else if (ninthQuestionInput.getVisibility() == View.VISIBLE) {
			// EditText question type is displayed to user
			String answerText = ninthQuestionInput.getText().toString().toLowerCase();
			if (answerText.equals(answerKey[head].toLowerCase())) {
				numberOfCorrectAnswers++;
			}
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm != null) {
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			} else {
				Log.d("MainActivity", "Soft keyboard not present");
			}
		} else {
			// Multiple CheckBox question type is displayed to user
			if (trackAnswers.isEmpty()) {
				displayToastForResult("Please check one or more of the options!");
			} else {
				if (trackAnswers.equals("ccc")) {
					numberOfCorrectAnswers++;
				}

				// Clear checked boxes when finish is clicked
				clearCheckedBoxGroup(view, true);
			}
		}
	}

	private void showSummary(View view) {
		// TODO
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
}
