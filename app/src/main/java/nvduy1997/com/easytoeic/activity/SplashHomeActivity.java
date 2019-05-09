package nvduy1997.com.easytoeic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nvduy1997.com.easytoeic.R;

public class SplashHomeActivity extends AppCompatActivity {

    private ImageView imgSplashHome, imgTree;
    private TextView txtSplash1, txtSplash2, txtSplash3;
    private Animation frombottom;
    private LinearLayout lvMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_home);
        imgSplashHome = findViewById(R.id.imgSplashHome);
        imgTree = findViewById(R.id.imgTree);
        txtSplash1 = findViewById(R.id.txtSplash1);
        txtSplash2 = findViewById(R.id.txtSplash2);
        txtSplash3 = findViewById(R.id.txtSplash3);
        lvMenu = findViewById(R.id.lvMenu);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        imgSplashHome.animate().translationY(-2000).setDuration(800).setStartDelay(300);
        imgTree.animate().alpha(0).setDuration(800).setStartDelay(600);
        txtSplash1.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(600);
        txtSplash2.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(600);
        txtSplash3.startAnimation(frombottom);
        lvMenu.startAnimation(frombottom);

    }
}
