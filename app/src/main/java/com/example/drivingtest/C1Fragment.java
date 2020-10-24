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

public class C1Fragment extends Fragment {

    ExamAdapter examAdapter;
    GridView gvExam;
    ArrayList<Exam> arr_exam =new ArrayList<Exam>();

    public C1Fragment() {
        // Required empty public constructor
    }


    public static C1Fragment newInstance() {
        C1Fragment fragment = new C1Fragment();

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
        return inflater.inflate(R.layout.fragment_c1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gvExam=(GridView) getActivity().findViewById(R.id.gvExamc1);
        arr_exam.add(new Exam("Exam 1"));
        arr_exam.add(new Exam("Exam 2"));
        arr_exam.add(new Exam("Exam 3"));
        arr_exam.add(new Exam("Exam 4"));

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