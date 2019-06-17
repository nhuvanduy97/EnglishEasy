package nvduy1997.com.easytoeic.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nvduy1997.com.easytoeic.Database.Dapan;
import nvduy1997.com.easytoeic.Database.DatabasManager;
import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.CheckAnswerPart1Adapter;
import nvduy1997.com.easytoeic.adapter.CheckAnswerPart5Adapter;
import nvduy1997.com.easytoeic.fragment.QuestionTestFragment;
import nvduy1997.com.easytoeic.model.Question;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionActivity extends FragmentActivity {

    private TextView tvTime, tvKiemTra;
    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    public static ArrayList<Dapan> array = new ArrayList<>();
    private CheckAnswerPart5Adapter adapter;
    private DatabasManager manager = new DatabasManager(this);
    private CountTime countTime;

    public int checkAns = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_question);
        initializeComponents();
        countTime = new CountTime(900000, 1000);
        countTime.start();



    }

    private void initializeComponents() {
        tvTime = findViewById(R.id.tvTimer);
        mPager = findViewById(R.id.pager_content);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        tvKiemTra = findViewById(R.id.tvKiemTra);
//        getDataQuestion();
        tvKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer();
            }
        });


    }

    //    private void getDataQuestion(){
//        DataService dataService = APIService.getService();
//        Call<List<Question>> callback = dataService.getQuestionP5Test1();
//        callback.enqueue(new Callback<List<Question>>() {
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                array = (ArrayList<Question>) response.body();
//                pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
//                mPager.setAdapter(pagerAdapter);
//                adapter = new CheckAnswerPart5Adapter(array,getApplicationContext());
//                tvKiemTra.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        CheckAnswer();
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//
//            }
//        });
//    }
    private void CheckAnswer() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("List Answers : ");
        dialog.setContentView(R.layout.diglog_check_answer);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = (int) (displayMetrics.heightPixels * 0.5f);
        int width = (int) (displayMetrics.widthPixels * 0.9f);

        dialog.getWindow().setLayout(width, height);

        Button btnCloseCheck, btnFinishCheck;
        GridView gvCheckAns = dialog.findViewById(R.id.gvCheckAns);
        adapter = new CheckAnswerPart5Adapter(manager.getAllDapAn(), this);
        gvCheckAns.setAdapter(adapter);

        gvCheckAns.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });

        btnCloseCheck = dialog.findViewById(R.id.btnCloseCheckPart1);
        btnFinishCheck = dialog.findViewById(R.id.btnFinishCheckPart1);

        btnCloseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnFinishCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns = 1;
                if(mPager.getCurrentItem() >= 2 ) mPager.setCurrentItem(mPager.getCurrentItem() - 2);
                else if (mPager.getCurrentItem() < 2 ) mPager.setCurrentItem(mPager.getCurrentItem() + 2);
                if (countTime != null){
                    countTime.cancel();
                }
                dialog.dismiss();



            }
        });
        dialog.show();
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

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return QuestionTestFragment.create(position,checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }


}
