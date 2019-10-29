package nvduy1997.com.easytoeic.activity;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.fragment.Part3SlideFragment;
import nvduy1997.com.easytoeic.model.QuestionPart3;

public class Part3SlideActivity extends FragmentActivity {

    private static final int NUM_PAGES = 10;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private TextView txtTestPart3, txtStartTimePart3, txtEndTimePart3, txtCheckPart3, txtScorePart3;
    private ImageView imgReturnPart3;
    private SeekBar sbListeningPart3;
    private ImageButton btnPlayPart3;
    private String audio, idTest, nameTest;
    private MediaPlayer mediaPlayer;
    public static ArrayList<QuestionPart3> part3ArrayList;
    public int checkAns = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part3_slide);

        mPager = findViewById(R.id.viewPagerPart3);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        initlizeComponents();

    }

    public void initlizeComponents() {
        txtTestPart3 = findViewById(R.id.txtTestPart3);
        txtStartTimePart3 = findViewById(R.id.txtStartTimePart3);
        txtEndTimePart3 = findViewById(R.id.txtEndTimePart3);
        txtCheckPart3 = findViewById(R.id.txtCheckPart3);
        txtScorePart3 = findViewById(R.id.txtScorePart3);
        imgReturnPart3 = findViewById(R.id.imgReturnPart3);
        sbListeningPart3 = findViewById(R.id.sbListeningPart3);
        btnPlayPart3 = findViewById(R.id.btnPlayPart3);


    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Part3SlideFragment.create(position, part3ArrayList, checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
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
