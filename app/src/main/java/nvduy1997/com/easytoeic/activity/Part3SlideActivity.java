package nvduy1997.com.easytoeic.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.common.Utils;
import nvduy1997.com.easytoeic.fragment.Part3SlideFragment;
import nvduy1997.com.easytoeic.model.QuestionPart3;
import nvduy1997.com.easytoeic.model.TestPart3;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("testPart3")) {
            TestPart3 testPart3 = intent.getParcelableExtra("testPart3");
            txtTestPart3.setText(testPart3.getTenTest());
            audio = testPart3.getAudioTest();
            idTest = testPart3.getIdTest();
            nameTest = testPart3.getTenTest();
        }

        getData(idTest);


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

    private void eventClick() {

    }

    class playAudioPart3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // tránh vấn đề bất đồng bộ
                        mediaPlayer.stop();
                        mediaPlayer.reset();

                    }
                });

                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeAudio();
            updateTime();
        }


    }

    private void timeAudio() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Utils.MM_SS);
        txtEndTimePart3.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sbListeningPart3.setMax(mediaPlayer.getDuration());
    }

    public void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sbListeningPart3.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Utils.MM_SS);
                }

            }
        }, 300);
    }


    private void getData(String id) {
        DataService dataService = APIService.getService();
        Call<List<QuestionPart3>> callback = dataService.getQuestionPart3(id);
        callback.enqueue(new Callback<List<QuestionPart3>>() {
            @Override
            public void onResponse(Call<List<QuestionPart3>> call, Response<List<QuestionPart3>> response) {
                part3ArrayList = (ArrayList<QuestionPart3>) response.body();
                pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                mPager.setAdapter(pagerAdapter);
            }

            @Override
            public void onFailure(Call<List<QuestionPart3>> call, Throwable t) {

            }
        });
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
