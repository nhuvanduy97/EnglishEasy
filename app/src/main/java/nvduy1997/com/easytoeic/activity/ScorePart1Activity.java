package nvduy1997.com.easytoeic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.QuestionPart1;

public class ScorePart1Activity extends AppCompatActivity {

    private Button btnAgain, btnScore;
    private ImageView imgReturnScore;
    private TextView txtTrue, txtFalse, txtNotAns, txtTotal;
    ArrayList<QuestionPart1> arr_QuesBegin = new ArrayList<>();

    int numNotAns = 0;
    int numTrue = 0;
    int numFalse = 0;
    int totalScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_part1);

        Intent intent = getIntent();
        arr_QuesBegin = intent.getExtras().getParcelableArrayList("arrQuess");

        Log.d("Score", "onCreate: "+arr_QuesBegin.get(5).getResultQuestion());

        initlizeComponents();
        checkResult();
        setScore();
    }

    public void initlizeComponents() {
        btnAgain = findViewById(R.id.btnAgain);
        imgReturnScore = findViewById(R.id.imgReturnScore);
        btnScore = findViewById(R.id.btnScore);
        txtTrue = findViewById(R.id.txtTrue);
        txtFalse = findViewById(R.id.txtFalse);
        txtNotAns = findViewById(R.id.txtNotAns);
        txtTotal = findViewById(R.id.txtTotal);

        imgReturnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void checkResult() {
        for (int i = 0; i < arr_QuesBegin.size(); i++) {
            if (arr_QuesBegin.get(i).getTraLoi().equals("") == true) {
                numNotAns++;
            } else if (arr_QuesBegin.get(i).getResultQuestion().equals(arr_QuesBegin.get(i).getTraLoi()) == true) {
                numTrue++;
            } else numFalse++;
        }
    }

    public void setScore() {
        txtNotAns.setText("" + numNotAns);
        txtTrue.setText("" + numTrue);
        txtFalse.setText("" + numFalse);
        totalScore = numTrue * 5;
        txtTotal.setText("" + totalScore);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
