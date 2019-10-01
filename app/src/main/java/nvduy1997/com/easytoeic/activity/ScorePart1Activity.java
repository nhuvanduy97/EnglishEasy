package nvduy1997.com.easytoeic.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.manager.DatabaseManager;
import nvduy1997.com.easytoeic.model.QuestionPart1;
import nvduy1997.com.easytoeic.model.Score;

public class ScorePart1Activity extends AppCompatActivity {

    private Button btnAgain, btnSaveScore;
    private ImageView imgReturnScore, imgShare;
    private TextView txtTrue, txtFalse, txtNotAns, txtTotal;
    ArrayList<QuestionPart1> arr_QuesBegin = new ArrayList<>();
    private String nameTest, date;
    int numNotAns = 0;
    int numTrue = 0;
    int numFalse = 0;
    int totalScore;
    private DatabaseManager databaseManager = new DatabaseManager(ScorePart1Activity.this);
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_score_part1);

        Intent intent = getIntent();
        if (intent != null) {
            arr_QuesBegin = intent.getExtras().getParcelableArrayList("arrQuess");
            nameTest = intent.getStringExtra("nameTest");
        }

        initlizeComponents();
        checkResult();
        setScore();
    }

    public void initlizeComponents() {
        imgShare = findViewById(R.id.imgShare);
        btnAgain = findViewById(R.id.btnAgain);
        imgReturnScore = findViewById(R.id.imgReturnScore);
        btnSaveScore = findViewById(R.id.btnSaveScore);
        txtTrue = findViewById(R.id.txtTrue);
        txtFalse = findViewById(R.id.txtFalse);
        txtNotAns = findViewById(R.id.txtNotAns);
        txtTotal = findViewById(R.id.txtTotal);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        imgReturnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScorePart1Activity.this);
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

        btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScorePart1Activity.this);
                builder.setTitle("Notification");
                builder.setIcon(R.drawable.document);
                builder.setMessage("Do you want to save score?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar = Calendar.getInstance();
                        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                        Score score = new Score(nameTest, "Part1", date, totalScore, numTrue, numFalse, numNotAns);
                        databaseManager.addScore(score);

                        Toast.makeText(ScorePart1Activity.this, "Save Score Successfull", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });
    }

    private void showMenu() {
        PopupMenu popupMenu = new PopupMenu(ScorePart1Activity.this, imgShare);
        popupMenu.getMenuInflater().inflate(R.menu.share_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.shareFB:
                        shareFaceBook();
                        //shareFB();
                        break;
                    case R.id.shareGG:
                        shareGoogle();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
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

    public void shareFaceBook() {
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(ScorePart1Activity.this, "Share Successfull", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(ScorePart1Activity.this, "Share Cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(ScorePart1Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setQuote("This is App Toeic TBD - " + nameTest + " - Part1 - Score : " + totalScore)
                .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=TRm4ge-P0As"))
                .build();

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            shareDialog.show(shareLinkContent);
        }

    }

    public void shareGoogle() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Part1 - " + nameTest + " - " + date + " - Total Score : " + totalScore);
        startActivity(Intent.createChooser(intent, "Share with"));
    }


}

