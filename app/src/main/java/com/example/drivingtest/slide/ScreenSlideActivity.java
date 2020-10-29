package com.example.drivingtest.slide;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.drivingtest.R;
import com.example.drivingtest.question.Question;
import com.example.drivingtest.question.QuestionController;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScreenSlideActivity extends FragmentActivity {

    private static final int NUM_PAGES = 10;


    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    TextView tvKiemTra, tvTimer, tvScore;
    public int checkAns=0;
    //DataBase
    QuestionController questionController;
    ArrayList<Question> arr_Ques;
    CounterClass timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        timer=new CounterClass(20*10000,1000);
        //Create a questionController for current viewpager
        questionController=new QuestionController(this);
        arr_Ques=new ArrayList<Question>();
        arr_Ques=questionController.getQuestion("b2");

        tvTimer =(TextView)findViewById(R.id.tvTimer);
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvScore=(TextView) findViewById(R.id.tvScore);
        //Redirect to Exam Result
        tvScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(ScreenSlideActivity.this, ResultActivity.class);
                intent.putExtra("arr_Ques",arr_Ques);
                startActivity(intent);
            }
        });
        timer.start();
        tvKiemTra =(TextView)findViewById(R.id.tvKiemTra);
        tvKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }



    public  ArrayList<Question> getData()
    {
        return arr_Ques;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        //Current position of screen
        public Fragment getItem(int position) {
            return  ScreenSlidePageFragment.create(position,checkAns); //Send current position to SlideFragment


        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    public  void checkAnswer()
    {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.check_answer_dialog);
        CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter(arr_Ques,this);
        GridView gvLsQuestion =(GridView) dialog.findViewById(R.id.gvLsQuestion);
        gvLsQuestion.setAdapter(answerAdapter);
        gvLsQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mPager.setCurrentItem(position);// Access to Question[i] Fragment
                dialog.dismiss();
            }
        });

        Button btnCancle, btnFinish;
        btnCancle=(Button) dialog.findViewById(R.id.btnCancle);
        btnFinish=(Button) dialog.findViewById(R.id.btnFinish);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                result();
                dialog.dismiss();

            }
        });
        dialog.show();

    }

    public void result()
    {
        checkAns=1;
        if(mPager.getCurrentItem()>=4) mPager.setCurrentItem(mPager.getCurrentItem()-4);
        else if (mPager.getCurrentItem() <4) mPager.setCurrentItem(mPager.getCurrentItem()+4);
        tvScore.setVisibility(View.VISIBLE);
        tvKiemTra.setVisibility(View.GONE);
    }
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime); //SetText For TextView Clock.
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");  //SetText For TextView Clock.
        }
    }


}