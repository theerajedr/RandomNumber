package com.example.t7.randomnumber.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.t7.randomnumber.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CorrectAnswerFragment extends Fragment {

    private Button restart_btn;
    private TextView secret_number_textview, try_count_textview;


    public CorrectAnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_correct_answer, container, false);

        secret_number_textview = (TextView) view.findViewById(R.id.secret_number_textview);
        try_count_textview = (TextView) view.findViewById(R.id.try_count_textview);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            secret_number_textview.setText("Correct Number : " + bundle.getInt("correct_number"));
            try_count_textview.setText("Try Count : "  + bundle.getInt("try_count"));
        }

        restart_btn = (Button) view.findViewById(R.id.restart_btn);

        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputAnswerFragment input_fragment = new InputAnswerFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame_layout, input_fragment);
                transaction.commit();
            }
        });

        return view;
    }

}
