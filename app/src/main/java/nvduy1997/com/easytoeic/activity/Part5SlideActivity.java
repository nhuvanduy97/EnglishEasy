package nvduy1997.com.easytoeic.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.CheckAnswerPart5Adapter;
import nvduy1997.com.easytoeic.fragment.Part5SlideFragment;
import nvduy1997.com.easytoeic.model.QuestionPart5;
import nvduy1997.com.easytoeic.model.TestPart5;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part5SlideActivity extends FragmentActivity {

    private static final int NUM_PAGES = 10;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private TextView tvTimerPart5, tvCheckPart5, tvScorePart5;
    private String nameTest, idTest;
    private ImageView btnReturn;

    public int checkAns = 0;

    public static ArrayList<QuestionPart5> arr_Question;

    private CounterClass timer;
    private int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mPager = findViewById(R.id.pager_content);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        initlizeComponents();

        Intent intent = getIntent();
        if (intent.hasExtra("testPart5")) {
            TestPart5 testPart5 = intent.getParcelableExtra("testPart5");
            idTest = testPart5.getIdTest();
            nameTest = testPart5.getTenTest();
        }

        getData(idTest);
        checkClick();
        showScore();

    }

    public void initlizeComponents() {
        tvTimerPart5 = findViewById(R.id.tvTimerPart5);
        tvCheckPart5 = findViewById(R.id.tvCheckPart5);
        tvScorePart5 = findViewById(R.id.tvScorePart5);
        btnReturn = findViewById(R.id.imgReturnPart5);
        //900s = 10 phut *1000(ra milis) va 1000 ben canh la buoc nhay (1s)
        totalTime = 10;
        timer = new CounterClass(totalTime*60 * 1000, 1000);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Part5SlideActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Notification");
                builder.setMessage("Do you want to exit?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });


        timer.start();
    }

    public void checkClick() {
        tvCheckPart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnsers();
            }
        });
    }

    public void checkAnsers() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("List Answers : ");
        dialog.setContentView(R.layout.diglog_check_answer);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = (int) (displayMetrics.heightPixels * 0.5f);
        int width = (int) (displayMetrics.widthPixels * 0.9f);

        dialog.getWindow().setLayout(width, height);

        Button btnCloseCheck, btnFinishCheck;

        CheckAnswerPart5Adapter checkAnswerPart5Adapter = new CheckAnswerPart5Adapter(arr_Question, this);
        GridView gvCheckAns = dialog.findViewById(R.id.gvCheckAns);
        gvCheckAns.setAdapter(checkAnswerPart5Adapter);

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
                resultScore();
                dialog.dismiss();


            }
        });
        dialog.show();
    }

    public void resultScore() {
        checkAns = 1;

        if (mPager.getCurrentItem() >= 5) {
            mPager.setCurrentItem(mPager.getCurrentItem() - 4);
        } else if (mPager.getCurrentItem() <= 5) {
            mPager.setCurrentItem(mPager.getCurrentItem() + 4);
        }

        tvScorePart5.setVisibility(View.VISIBLE);
        tvCheckPart5.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Part5SlideFragment.create(position, arr_Question, checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public void getData(String id) {
        DataService dataService = APIService.getService();
        Call<List<QuestionPart5>> callback = dataService.getQuestionPart5(id);
        callback.enqueue(new Callback<List<QuestionPart5>>() {
            @Override
            public void onResponse(Call<List<QuestionPart5>> call, Response<List<QuestionPart5>> response) {

                arr_Question = (ArrayList<QuestionPart5>) response.body();
                pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                mPager.setAdapter(pagerAdapter);
            }

            @Override
            public void onFailure(Call<List<QuestionPart5>> call, Throwable t) {

            }
        });
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
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

    public void showScore() {
        tvScorePart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part5SlideActivity.this, ScorePart5Activity.class);
                intent.putExtra("arrQuess", arr_Question);
                intent.putExtra("nameTest", nameTest);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimerPart5.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvTimerPart5.setText("00:00");  //SetText cho textview hiện thị thời gian.
            resultScore();
        }
    }

}
