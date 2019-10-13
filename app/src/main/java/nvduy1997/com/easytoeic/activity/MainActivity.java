
package nvduy1997.com.easytoeic.activity;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.fragment.DetailGrammarFragment;
import nvduy1997.com.easytoeic.fragment.GrammarFragment;
import nvduy1997.com.easytoeic.fragment.HomeFragment;
import nvduy1997.com.easytoeic.fragment.ListeningFragment;
import nvduy1997.com.easytoeic.fragment.ReadingFragment;
import nvduy1997.com.easytoeic.fragment.ScoreStatisticsFragment;
import nvduy1997.com.easytoeic.fragment.TopicVocabularyFragment;

import nvduy1997.com.easytoeic.fragment.VOAFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , GrammarFragment.OpenFragmentDetail
        , HomeFragment.OpenRead
        , HomeFragment.OpenDic
        , HomeFragment.OpenVOA
        , HomeFragment.OpenGrammar
        , HomeFragment.OpenListenning
        , HomeFragment.OpenVocabulary {

    private ReadingFragment readingFragment;
    private Toolbar toolbar;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, homeFragment);
        transaction.commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.vocabolary) {
            if (isConnected()) {
                openVocabulary();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.nowifi);
                builder.setTitle("Notification");
                builder.setMessage("You not connect network!");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }


        } else if (id == R.id.mini_test) {

        } else if (id == R.id.listen_engish) {
            if (isConnected()) {
                openListenning();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.nowifi);
                builder.setTitle("Notification");
                builder.setMessage("You not connect network!");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }

        } else if (id == R.id.readding_engish) {
            if (isConnected()) {
                readingFragment = new ReadingFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, readingFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.nowifi);
                builder.setTitle("Notification");
                builder.setMessage("You not connect network!");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }


        } else if (id == R.id.grammar_english) {
            if (isConnected()) {
                GrammarFragment grammarFragment = new GrammarFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, grammarFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.nowifi);
                builder.setTitle("Notification");
                builder.setMessage("You not connect network!");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }

        } else if (id == R.id.dictionary) {
            openDic();

        } else if (id == R.id.voa) {
            if (isConnected()) {
                openVOA();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.nowifi);
                builder.setTitle("Notification");
                builder.setMessage("You not connect network!");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }

        } else if (id == R.id.login) {

        } else if (id == R.id.share) {

        } else if (id == R.id.about_me) {

        } else if (id == R.id.homeApp) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, homeFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void Open(int id) {
        DetailGrammarFragment detailGrammarFragment = new DetailGrammarFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("KEY", id);
        detailGrammarFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailGrammarFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void openRead() {
        readingFragment = new ReadingFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, readingFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void openDic() {
        ScoreStatisticsFragment scoreStatisticsFragment = new ScoreStatisticsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, scoreStatisticsFragment);
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void openVOA() {
        VOAFragment voaFragment = new VOAFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, voaFragment);
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void openGrammar() {
        GrammarFragment grammarFragment = new GrammarFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, grammarFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void openListenning() {
        ListeningFragment listeningFragment = new ListeningFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, listeningFragment);
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void openVocabulary() {
        TopicVocabularyFragment topicVocabularyFragment = new TopicVocabularyFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, topicVocabularyFragment);
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

}
