package com.example.drivingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.drivingtest.slide.ScreenSlideActivity;
import com.example.drivingtest.ui.ExamAdapter;

import java.util.ArrayList;

public class B1Fragment extends Fragment {

    ExamAdapter examAdapter;
    GridView gvExam;
    ArrayList<Exam> arr_exam =new ArrayList<Exam>();

    public B1Fragment() {
        // Required empty public constructor
    }


    public static B1Fragment newInstance() {
        B1Fragment fragment = new B1Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gvExam=(GridView) getActivity().findViewById(R.id.gvExam);
        arr_exam.add(new Exam("Exam 1"));
        arr_exam.add(new Exam("Exam 2"));
        arr_exam.add(new Exam("Exam 3"));
        arr_exam.add(new Exam("Exam 4"));
        arr_exam.add(new Exam("Exam 5"));
        arr_exam.add(new Exam("Exam 6"));
        arr_exam.add(new Exam("Exam 7"));
        arr_exam.add(new Exam("Exam 8"));
        examAdapter =new ExamAdapter(getActivity(),arr_exam);
        gvExam.setAdapter(examAdapter);
        gvExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getActivity(),ScreenSlideActivity.class);
                startActivity(intent);
            }
        });


    }
}