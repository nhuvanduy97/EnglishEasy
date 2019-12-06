package nvduy1997.com.easytoeic.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.QuestionPart7;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part7SlideActivity extends FragmentActivity {
    private static final int NUM_PAGES = 10;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private TextView tvTimerPart7, tvCheckPart7, tvScorePart7;
    private String nameTest, idTest;
    private ImageView btnReturn;

    public int checkAns = 0;

    public static ArrayList<QuestionPart7> arr_Question;

    private Part5SlideActivity.CounterClass timer;
    private int totalTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part7_slide);

        mPager = findViewById(R.id.pager_content);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        initlizeComponents();

    }

    private void initlizeComponents() {

        tvTimerPart7 = findViewById(R.id.tvTimerPart7);
        tvCheckPart7 = findViewById(R.id.tvCheckPart7);
        tvScorePart7 = findViewById(R.id.tvScorePart7);
        btnReturn = findViewById(R.id.imgReturnPart7);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Part7SlideActivity.this);
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
    }

    public void getData(String id) {
        DataService dataService = APIService.getService();
        Call<List<QuestionPart7>> callback = dataService.getQuestionPart7(id);
        callback.enqueue(new Callback<List<QuestionPart7>>() {
            @Override
            public void onResponse(Call<List<QuestionPart7>> call, Response<List<QuestionPart7>> response) {

                arr_Question = (ArrayList<QuestionPart7>) response.body();
                pagerAdapter = new Part7SlideActivity.ScreenSlidePagerAdapter(getSupportFragmentManager());
                mPager.setAdapter(pagerAdapter);
            }

            @Override
            public void onFailure(Call<List<QuestionPart7>> call, Throwable t) {

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

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //return Part7SlideFragment.create(position, arr_Question, checkAns);
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
