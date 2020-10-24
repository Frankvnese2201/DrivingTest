package com.example.drivingtest.slide;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivingtest.R;
import com.example.drivingtest.question.Question;

import java.util.ArrayList;

public class ScreenSlidePageFragment extends Fragment {
    ArrayList<Question> arr_Quest;
    private int mPageNumber; //position of current page
    public int checkAns;// Declare variable to check answer
    TextView tvNum;
    TextView tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA,radB,radC,radD;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Attach Question with viewPaper e.x: question 1 attached to slide 1
        arr_Quest=new ArrayList<Question>();
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        arr_Quest= slideActivity.getData(); //get data to array question

        mPageNumber= getArguments().getInt("page"); //get position
        checkAns=getArguments().getInt("checkAnswer");
    }

    public  static ScreenSlidePageFragment create(int pageNumber, int checkAnswer)
    {
        //send position
        ScreenSlidePageFragment screenSlidePageFragment= new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page",pageNumber);
        bundle.putInt("checkAnswer",checkAnswer);
        screenSlidePageFragment.setArguments(bundle);
        return screenSlidePageFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        tvNum = (TextView) rootView.findViewById(R.id.tvNum);
        tvQuestion =(TextView) rootView.findViewById(R.id.tvQuestion);
        radA= (RadioButton) rootView.findViewById(R.id.radA);
        radB= (RadioButton) rootView.findViewById(R.id.radB);
        radC= (RadioButton) rootView.findViewById(R.id.radC);
        radD= (RadioButton) rootView.findViewById(R.id.radD);
        radioGroup=(RadioGroup) rootView.findViewById(R.id.radGroup);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Get Question and Answers from Array Question and Array Answer using PageNumber
        tvNum.setText("Question "+ (mPageNumber+1));
        tvQuestion.setText(getItem(mPageNumber).getQuestion());
        radA.setText(getItem(mPageNumber).getAnswer1());
        radB.setText(getItem(mPageNumber).getAnswer2());
        radC.setText(getItem(mPageNumber).getAnswer3());
        radD.setText(getItem(mPageNumber).getAnswer4());
        if (checkAns!=0)
        {
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(mPageNumber).choiceID = checkedId;
                getItem(mPageNumber).setUserAnswer(getChoiceFromID(checkedId));
                 //Toast.makeText(getActivity(),"This is answer "+ checkedId, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public  Question getItem(int position)
    {
        return arr_Quest.get(position);
    }
    //Get value of (position) radioGroup and convert AnswerID A/B/C/D
    private  String getChoiceFromID(int ID)
    {
        if(ID == R.id.radA)
        {
            return "1";
        };
        if(ID==R.id.radB)
        {
            return "2";
        };
        if(ID==R.id.radC)
        {
            return "3";
        };
        if(ID==R.id.radD)
        {
            return "4";
        }
        return "";
    }
    //highlight answer
    private void getCheckAns(String ans)
    {
        if(ans.equals("1")==true)
        {
            radA.setBackgroundColor(Color.RED);
        }
        if(ans.equals("2")==true)
        {
            radB.setBackgroundColor(Color.RED);
        }
        if(ans.equals("3")==true)
        {
            radC.setBackgroundColor(Color.RED);
        }
        if(ans.equals("4")==true) {
            radD.setBackgroundColor(Color.RED);
        };
    }
}