package nvduy1997.com.easytoeic.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.QuestionTestAdapter;
import nvduy1997.com.easytoeic.fragment.QuestionTestFragment;
import nvduy1997.com.easytoeic.model.Question;


public class QuestionActivity extends AppCompatActivity {

    private TextView tvTime, tvKiemTra;
    QuestionTestFragment questionTestFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_question);
        initializeComponents();
        CountTime countTime = new CountTime(900000,1000);
        countTime.start();
        Bundle bundle = getIntent().getExtras();
         questionTestFragment = new QuestionTestFragment();
        questionTestFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.RelativeLayout_Content_Question, questionTestFragment);
        transaction.commit();
    }

    private void initializeComponents() {
        tvTime = findViewById(R.id.tvTimer);

        tvKiemTra = findViewById(R.id.tvKiemTra);
        tvKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("data",Context.MODE_PRIVATE);
                String a = sharedPreferences.getString("id","");
                Log.d("onClick", "onClick: " + a);
            }
        });
    }

    public class CountTime extends CountDownTimer {


        public CountTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            @SuppressLint("DefaultLocale") String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTime.setText(countTime);

        }

        @Override
        public void onFinish() {
            tvTime.setText("00:00");
        }
    }

    public void Save(String str){
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id",str);
        editor.apply();
    }







}
