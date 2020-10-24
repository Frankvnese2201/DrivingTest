package com.example.drivingtest.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drivingtest.Exam;
import com.example.drivingtest.R;

import java.util.ArrayList;

public class ExamAdapter extends ArrayAdapter<Exam> {
    public ExamAdapter(@NonNull Context context, ArrayList<Exam> exam) {
        super(context, 0, exam);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView= inflater.inflate(R.layout.item_gridview,parent,false);

            TextView tvName= (TextView) convertView.findViewById(R.id.tvNumExam);
            ImageView imgIcon =(ImageView) convertView.findViewById(R.id.imgIcon);

            Exam p=getItem(position);
            if(p!= null)
            {
                tvName.setText(""+p.getName());
                imgIcon.setImageResource(R.drawable.ic_menu_gallery);
            }
        }
        return convertView;
    }
}
