package com.example.peethr.wsbtest.presenters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.preferences.ManageSharedPreferences;

import java.util.Timer;
import java.util.TimerTask;

public class SelectLanguage extends AppCompatActivity {

    private TextSwitcher welcomeTextSwitcher;
    private TextSwitcher languageTextSwitcher;
    private Button selectLanguageButton;
    private Button buttonFlagPolish;
    private Button buttonFlagUk;
    private Button buttonFlagRussia;


    private String welcome[] = {"Hello", "Witaj", "Здравствуйте"};
    private int currentIndex = 0;

    private ManageSharedPreferences manageSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        findViews();

        setupWelcomeTextSwitcher();
        setupLanguageTextSwitcher();

        flagButtonsActions();

        Thread changeWelcomeText = new Thread()
        {
            @Override
            public void run() {

                Timer timer = new Timer("timer_welcome");
                timer.scheduleAtFixedRate(
                        new TimerTask() {
                            public void run() {
                                //switch text using runOnUiThread()
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        // If index reaches maximum reset it
                                        if (currentIndex == welcome.length) {
                                            currentIndex = 0;
                                        }

                                        welcomeTextSwitcher.setText(welcome[currentIndex]);

                                        currentIndex++;
                                    }
                                });
                            }
                        }, 0, 2000);

            }
        };
        changeWelcomeText.start();


        selectLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SelectLanguage.this, SplashScreen.class);
                startActivity(intent);

                finish();

            }
        });

    }

    private void flagButtonsActions() {
        // Action for clicking on polish flag
        buttonFlagPolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageTextSwitcher.setText("Polski");

                selectLanguageButton.setText("Dalej");

                // Animate button
                ObjectAnimator nextButtonTranslationY = ObjectAnimator.ofFloat(
                        selectLanguageButton,
                        "translationY",
                        200, 0);

                AnimatorSet animateButton = new AnimatorSet();
                animateButton.playTogether(nextButtonTranslationY);
                animateButton.start();

                // set in-app language
                manageSharedPreferences.setLanguage("pl");
            }
        });

        // Action for clicking on russian flag
        buttonFlagRussia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageTextSwitcher.setText("русский");

                selectLanguageButton.setText("далее");

                ObjectAnimator nextButtonTranslationY = ObjectAnimator.ofFloat(
                        selectLanguageButton,
                        "translationY",
                        200, 0);

                AnimatorSet animateButton = new AnimatorSet();
                animateButton.playTogether(nextButtonTranslationY);
                animateButton.start();

                // set in-app language
                manageSharedPreferences.setLanguage("ru");
            }
        });

        // Action for clicking on UK flag
        buttonFlagUk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageTextSwitcher.setText("English");

                selectLanguageButton.setText("Next");

                ObjectAnimator nextButtonTranslationY = ObjectAnimator.ofFloat(
                        selectLanguageButton,
                        "translationY",
                        200, 0);

                AnimatorSet animateButton = new AnimatorSet();
                animateButton.playTogether(nextButtonTranslationY);
                animateButton.start();

                // set in-app language
                manageSharedPreferences.setLanguage("en");
            }
        });
    }

    // kill app when back pressed, prevent from sending to splash without language selected
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }

    private void findViews() {

        manageSharedPreferences = new ManageSharedPreferences(this);

        selectLanguageButton = findViewById(R.id.selectLanguageButton);
        buttonFlagPolish = findViewById(R.id.buttonFlagPolish);
        buttonFlagRussia = findViewById(R.id.buttonFlagRussia);
        buttonFlagUk = findViewById(R.id.buttonFlagUk);
        languageTextSwitcher = findViewById(R.id.languageTextSwitcher);
        welcomeTextSwitcher = findViewById(R.id.welcomeTextSwitcher);
    }

    private void setupLanguageTextSwitcher() {
        // Create TextView that will show our text
        languageTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView languageText = new TextView(SelectLanguage.this);
                languageText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                languageText.setTextSize(36);
                return languageText;
            }
        });

        languageTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        languageTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);
    }

    private void setupWelcomeTextSwitcher() {
        // Create TextView that will show our text
        welcomeTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView welcomeText = new TextView(SelectLanguage.this);
                welcomeText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                welcomeText.setTextSize(36);
                return welcomeText;
            }
        });

        welcomeTextSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        welcomeTextSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
