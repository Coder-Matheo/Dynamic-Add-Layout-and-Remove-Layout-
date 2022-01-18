package com.dynamiclayoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    List<String> teamList = new ArrayList<>();
    Button buttonSubmitList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmitList = findViewById(R.id.button_submit_list);

        buttonAdd.setOnClickListener(this);
        buttonSubmitList.setOnClickListener(this);


        teamList.add("Team");
        teamList.add("India");
        teamList.add("Australia");
        teamList.add("England");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_add:
                addView();
                break;
            case R.id.button_submit_list:
                checkIfValidAndRead();
                break;
        }

    }

    private void checkIfValidAndRead() {


        for (int i = 0; i <layoutList.getChildCount(); i++){
            View cricketerView = layoutList.getChildAt(i);
            EditText editTextName = cricketerView.findViewById(R.id.edit_cricketer_name);
            AppCompatSpinner spinnerTeam = cricketerView.findViewById(R.id.spinner_team);

            Toast.makeText(this, ""+ editTextName.getText().toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ""+ spinnerTeam.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

        }
    }

    private void addView() {
        final View cricketerView = getLayoutInflater().inflate(R.layout.row_add_cricketer, null, false);
        //EditText editText = cricketerView.findViewById(R.id.edit_cricketer_name);
        AppCompatSpinner spinnerTeam = cricketerView.findViewById(R.id.spinner_team);
        ImageView imageClose = cricketerView.findViewById(R.id.image_remove);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, teamList);
        spinnerTeam.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(cricketerView);
            }
        });

        layoutList.addView(cricketerView);
    }

    private void removeView(View view) {
        layoutList.removeView(view);
    }
}