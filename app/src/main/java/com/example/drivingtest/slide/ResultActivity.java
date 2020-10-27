package com.example.drivingtest.slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.drivingtest.R;
import com.example.drivingtest.question.Question;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    ArrayList<Question> arr_QuesBegin= new ArrayList<Question>();
    int correctAns=0, wrongAns=0, noneAns=0, totalScore=0;

    TextView tvTrueAns,tvWrongAns,tvNoneAns,tvTotalScore;
    Button btnAgain,btnExit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        arr_QuesBegin= (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");
        begin();
        checkResult();
        tvTrueAns.setText(""+correctAns);
        tvWrongAns.setText(""+wrongAns);
        tvNoneAns.setText(""+noneAns);
        tvTotalScore.setText(""+totalScore);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(ResultActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("System Message");
                builder.setMessage("Do You Want To Exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }

    public  void begin()
    {
        tvWrongAns =(TextView) findViewById(R.id.tvWrongAns);
        tvTrueAns =(TextView) findViewById(R.id.tvTrueAns);
        tvNoneAns =(TextView) findViewById(R.id.tvNoneAns);
        tvTotalScore=(TextView) findViewById(R.id.tvTotalScore);
        btnAgain =(Button) findViewById(R.id.btnAgain);
        btnExit =(Button) findViewById(R.id.btnExit);

    }
    //Check Result
    public  void checkResult()
    {
        for(int i=0;i<arr_QuesBegin.size();i++)
        {
            if(arr_QuesBegin.get(i).getUserAnswer().equals("")==true)
            {
                noneAns++;
            } else if (arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getUserAnswer())==true)
            {
                correctAns++;
            }else wrongAns++;
        }
        totalScore=correctAns*10;
    }
}