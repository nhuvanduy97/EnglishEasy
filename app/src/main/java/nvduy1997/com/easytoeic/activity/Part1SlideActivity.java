package nvduy1997.com.easytoeic.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.CheckAnswerPart1Adapter;
import nvduy1997.com.easytoeic.fragment.Part1SlideFragment;
import nvduy1997.com.easytoeic.model.QuestionPart1;
import nvduy1997.com.easytoeic.model.TestPart1;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part1SlideActivity extends FragmentActivity {
    private static final int NUM_PAGES = 10;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private TextView txtTest, txtStartTime, txtEndTime, txtCheck, txtScorePart1;
    private ImageView imgReturn;
    private SeekBar sbListening;
    private ImageButton btnPlay;
    private String audio, idTest, nameTest;
    private MediaPlayer mediaPlayer;
    public static ArrayList<QuestionPart1> part1ArrayList;
    public int checkAns = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1_slide);

        mPager = findViewById(R.id.viewPagerPart1);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        initlizeComponents();

        Intent intent = getIntent();
        if (intent.hasExtra("testPart1")) {
            TestPart1 testPart1 = intent.getParcelableExtra("testPart1");
            txtTest.setText(testPart1.getTenTest());
            audio = testPart1.getHinhTest();
            idTest = testPart1.getIdTest();
            nameTest = testPart1.getTenTest();
        }

        getData(idTest);
        eventClick();
        checkClick();
        showScore();
    }

    private void eventClick() {
        new playAudioPart1().execute(audio);
        btnPlay.setImageResource(R.drawable.pausepart1);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.playpart1);
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pausepart1);
                }
            }
        });

        sbListening.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbListening.getProgress());

            }
        });
    }

    public ArrayList<QuestionPart1> returnData() {
        return part1ArrayList;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Part1SlideFragment.create(position, part1ArrayList, checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public void initlizeComponents() {
        txtScorePart1 = findViewById(R.id.txtScorePart1);
        txtCheck = findViewById(R.id.txtCheckPart1);
        sbListening = findViewById(R.id.sbListening);
        txtTest = findViewById(R.id.txtTest);
        imgReturn = findViewById(R.id.imgReturn);
        txtEndTime = findViewById(R.id.txtEndTime);
        txtStartTime = findViewById(R.id.txtStartTime);
        btnPlay = findViewById(R.id.btnPlayPart1);

        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Part1SlideActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Notification");
                builder.setMessage("Do you want to exit?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        mediaPlayer.stop();
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

    public void checkClick() {
        txtCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                mediaPlayer.pause();
                btnPlay.setImageResource(R.drawable.playpart1);
            }
        });
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

    class playAudioPart1 extends AsyncTask<String, Void, String> {

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtEndTime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sbListening.setMax(mediaPlayer.getDuration());
    }

    public void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sbListening.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtStartTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.pause();
                            btnPlay.setImageResource(R.drawable.playpart1);
                        }
                    });
                }
            }
        }, 300);
    }

    public void getData(String id) {
        DataService dataService = APIService.getService();
        Call<List<QuestionPart1>> callback = dataService.getQuestionPart1(id);
        callback.enqueue(new Callback<List<QuestionPart1>>() {
            @Override
            public void onResponse(Call<List<QuestionPart1>> call, Response<List<QuestionPart1>> response) {

                part1ArrayList = (ArrayList<QuestionPart1>) response.body();
                pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                mPager.setAdapter(pagerAdapter);

            }

            @Override
            public void onFailure(Call<List<QuestionPart1>> call, Throwable t) {

            }
        });
    }

    public void checkAnswer() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("List Answers : ");
        dialog.setContentView(R.layout.diglog_check_answer);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = (int) (displayMetrics.heightPixels * 0.5f);
        int width = (int) (displayMetrics.widthPixels * 0.9f);

        dialog.getWindow().setLayout(width, height);

        Button btnCloseCheck, btnFinishCheck;

        CheckAnswerPart1Adapter checkAnswerPart1Adapter = new CheckAnswerPart1Adapter(part1ArrayList, this);
        Log.e("checkAnswer", "checkAnswer: " + part1ArrayList.size());
        GridView gvCheckAns = dialog.findViewById(R.id.gvCheckAns);
        gvCheckAns.setAdapter(checkAnswerPart1Adapter);

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

        txtScorePart1.setVisibility(View.VISIBLE);
        txtCheck.setVisibility(View.GONE);
    }

    public void showScore() {
        txtScorePart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part1SlideActivity.this, ScorePart1Activity.class);
                intent.putExtra("arrQuess", part1ArrayList);
                intent.putExtra("nameTest", nameTest);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}

