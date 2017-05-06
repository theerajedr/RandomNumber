package com.example.t7.randomnumber;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.t7.randomnumber.Fragments.InputAnswerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputAnswerFragment input_fragment = new InputAnswerFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_layout, input_fragment);
        transaction.commit();
    }
}
