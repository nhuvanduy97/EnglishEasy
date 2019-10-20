package nvduy1997.com.easytoeic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
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
    public int checkAns = 0;

    public static ArrayList<QuestionPart5> arr_Question;

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

    }

    public void initlizeComponents() {
        tvTimerPart5 = findViewById(R.id.tvTimerPart5);
        tvCheckPart5 = findViewById(R.id.tvCheckPart5);
        tvScorePart5 = findViewById(R.id.tvScorePart5);
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
}
