package com.example.t7.randomnumber.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.t7.randomnumber.R;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Random;

import com.example.t7.randomnumber.API.RandomNumberApi;
import com.example.t7.randomnumber.POJO.RandomNumberResponseData;
import com.example.t7.randomnumber.POJO.RandomNumberSendRequestData;
import com.example.t7.randomnumber.POJO.RandomNumberSendRequestParams;
import io.reactivex.Observable;
import io.reactivex.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputAnswerFragment extends Fragment {

    private EditText answer_number_edittext;
    private Button submit_btn, show_number_btn;
    private TextView correct_number_textview, try_count_textview, hint_textview;
    private ProgressDialog progressDialog;

    private RandomNumberResponseData randomNumberResponseData;

    private final String SETTING_JSON_RPC_VERSION = "2.0";
    private final String SETTING_METHOD = "generateIntegers";
    private final String SETTING_API_KEY = "d0e22477-f3a3-4bad-a6b9-46fdfcd25463";
    private final int SETTING_ID = 32078;
    private final int SETTING_N = 1000;
    private final int SETTING_MIN = 1000;
    private final int SETTING_MAX = 9999;
    private final boolean SETTING_REPLACEMENT = true;
    private final int SETTING_BASE = 10;
    private final String SETTING_API_URL = "https://api.random.org/json-rpc/1/";

    private int response_number = 0;
    private int try_count = 0;

    private boolean show_secret_number_toggle = false;

    public InputAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_answer, container, false);

        answer_number_edittext = (EditText) view.findViewById(R.id.answer_number_edittext);
        submit_btn = (Button) view.findViewById(R.id.submit_btn);
        correct_number_textview = (TextView) view.findViewById(R.id.correct_number_textview);
        try_count_textview = (TextView) view.findViewById(R.id.try_count_textview);
        show_number_btn = (Button) view.findViewById(R.id.show_number_btn);
        hint_textview = (TextView) view.findViewById(R.id.hint_textview);

        initData();

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answer_number_edittext.getText().length() > 4 || answer_number_edittext.getText().toString().isEmpty()){
                    answer_number_edittext.setError("Please Input Number 1000 - 9999");
                    return;
                }

                int answer_number;
                answer_number = Integer.parseInt(answer_number_edittext.getText().toString());

                //business logic
                if(answer_number >= SETTING_MIN
                        && answer_number <= SETTING_MAX){

                    if(response_number > 0){
                        /*if(response_number == answer_number){
                            //correct

                            Bundle bundle = new Bundle();
                            bundle.getInt("correct_number", response_number);
                            bundle.getInt("try_count", try_count);

                            CorrectAnswerFragment correctAnswerFragment = new CorrectAnswerFragment();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame_layout, correctAnswerFragment);
                            transaction.commit();
                        } else if (response_number < answer_number){
                            //wrong case "<"
                            try_count++;
                            setTryCountText(try_count);
                        } else {

                        }*/

                        if(answer_number > response_number){
                            hint_textview.setText(answer_number + " > Secret Number");

                            try_count++;
                            setTryCountText(try_count);
                        } else if(answer_number < response_number){
                            hint_textview.setText(answer_number + " < Secret Number");

                            try_count++;
                            setTryCountText(try_count);
                        } else {
                            //correct
                            Bundle bundle = new Bundle();
                            bundle.putInt("correct_number", response_number);
                            bundle.putInt("try_count", try_count);

                            CorrectAnswerFragment correctAnswerFragment = new CorrectAnswerFragment();
                            correctAnswerFragment.setArguments(bundle);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame_layout, correctAnswerFragment);
                            transaction.commit();
                        }
                    }
                } else {
                    answer_number_edittext.setError("Please Input Number 1000 - 9999");
                }
            }
        });

        show_number_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_secret_number_toggle){
                    correct_number_textview.setVisibility(View.GONE);
                    show_secret_number_toggle = false;
                }else{
                    correct_number_textview.setVisibility(View.VISIBLE);
                    show_secret_number_toggle = true;
                }
            }
        });

        return view;
    }

    private void initData(){

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //setting request data
        RandomNumberSendRequestData sendRequestData = new RandomNumberSendRequestData();
        sendRequestData.setJsonrpc(SETTING_JSON_RPC_VERSION);
        sendRequestData.setMethod(SETTING_METHOD);
        sendRequestData.setId(SETTING_ID);

        //set params
        RandomNumberSendRequestParams params = new RandomNumberSendRequestParams();
        params.setApiKey(SETTING_API_KEY);
        params.setN(SETTING_N);
        params.setMin(SETTING_MIN);
        params.setMax(SETTING_MAX);
        params.setReplacement(SETTING_REPLACEMENT);
        params.setBase(SETTING_BASE);

        sendRequestData.setParams(params);

        //call api
        randomNumberResponseData = new RandomNumberResponseData();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SETTING_API_URL)
                .build();

        RandomNumberApi randomNumberApi = retrofit.create(RandomNumberApi.class);
        Observable<RandomNumberResponseData> random_number_data = randomNumberApi.getRandomNumber(sendRequestData);

        if(random_number_data != null){
            random_number_data
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RandomNumberResponseData>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RandomNumberResponseData value) {
                            Log.e("InputAnswerFragment", "onNext : " + value.getResult().getRandom().getData().get(0));
                            randomNumberResponseData = value;
                            response_number = value.getResult().getRandom().getData().get(0);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("InputAnswerFragment", "onError : " + e.toString());
                            response_number =  randomNumber(SETTING_MIN, SETTING_MAX);

                            if(response_number > 0){
                                correct_number_textview.setText(String.valueOf(response_number));
                            }

                            if(progressDialog != null){
                                if(progressDialog.isShowing()){
                                    progressDialog.dismiss();
                                }
                            }
                        }

                        @Override
                        public void onComplete() {
                            Log.e("InputAnswerFragment", "onComplete");

                            if(response_number > 0){
                                correct_number_textview.setText(String.valueOf(response_number));
                            }

                            if(progressDialog != null){
                                if(progressDialog.isShowing()){
                                    progressDialog.dismiss();
                                }
                            }

                        }
                    });

        }

    }

    private int randomNumber(int min, int max){
        int random_number;

        Random random = new Random();
        random_number = random.nextInt((max - min) + 1) + min;

        return random_number;
    }

    private void setTryCountText(int try_count){
        try_count_textview.setText("Try : " + String.valueOf(try_count));
    }

}
