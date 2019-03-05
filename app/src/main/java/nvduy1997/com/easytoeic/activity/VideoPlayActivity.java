package nvduy1997.com.easytoeic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.fragment.VOAFragment;

public class VideoPlayActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private String id = "";
    private int REQUEST_VIDEO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        initlizeComponents();
    }

    public void initlizeComponents() {
        youTubePlayerView = findViewById(R.id.myYoutube);
        Intent intent = getIntent();
        id = intent.getStringExtra("idVideoYoutube");
        youTubePlayerView.initialize(VOAFragment.API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(id);
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(VideoPlayActivity.this, REQUEST_VIDEO);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO) {
            youTubePlayerView.initialize(VOAFragment.API_KEY, VideoPlayActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
