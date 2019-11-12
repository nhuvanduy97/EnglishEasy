package nvduy1997.com.easytoeic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nvduy1997.com.easytoeic.R;

public class SettingActivity extends AppCompatActivity {
    private TextView txtSetting;
    private ImageView imgReturnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initlizeComponents();
    }

    private void initlizeComponents() {
        txtSetting = findViewById(R.id.txtSetting);
        imgReturnSetting = findViewById(R.id.imgReturnSetting);
        imgReturnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
