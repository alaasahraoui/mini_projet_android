package com.example.myapp6;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends  AppCompatActivity {
    private Handler myhandler=new Handler();

















    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    public ProgressBar progressBar, myProgressBar;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private long backPressedTime;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private LinearLayout life_layout;
    private int score;
    private boolean answered;
public int progress_status=0;
public int erreur=0;

private ImageView life1;
private ImageView life2;
private ImageView life3;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);













        new Thread(new Runnable() {

            @Override
            public void run() {
                while (progress_status < 100){
                    progress_status+=3.33;
                    android.os.SystemClock.sleep(1000);
                    myhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            myProgressBar.setProgress(( progress_status));
                        }
                    });
                }




            }






        }).start();






        life1=findViewById(R.id.life1);
        life2=findViewById(R.id.life2);
        life3=findViewById(R.id.life3);

        textViewQuestion = findViewById(R.id.qst_textview);
        textViewScore = findViewById(R.id.score_textview);


        myProgressBar = (ProgressBar)findViewById(R.id.myprogressbar);



        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.timer_textview);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.rd1);
        rb2 = findViewById(R.id.rd2);
        rb3 = findViewById(R.id.rd3);
        buttonConfirmNext = findViewById(R.id.next_btn_quiz);
        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();





        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);






        showNextQuestion();



        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }











public void lifes_nbr(){


        erreur=questionCounter-score;

         if (erreur==1){life1.setVisibility(View.GONE);}
         if (erreur==2){life2.setVisibility(View.GONE);}
         if (erreur==3){life3.setVisibility(View.GONE);}
         if (erreur==4){life3.setVisibility(View.GONE); finishQuiz();}



    }



    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);

        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;

            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");


            timeLeftInMillis = COUNTDOWN_IN_MILLIS;




            startCountDown();
        } else {
            finishQuiz();
        }
    }




    private void startCountDown() {










        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;


                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }




    private void updateCountDownText() {

        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }


    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);

        }
        lifes_nbr();

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {


        Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
        finish();
    }



    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }












    }





