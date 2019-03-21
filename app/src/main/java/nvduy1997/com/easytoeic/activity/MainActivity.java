
package nvduy1997.com.easytoeic.activity;

import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.fragment.DetailGrammarFragment;
import nvduy1997.com.easytoeic.fragment.GrammarFragment;
import nvduy1997.com.easytoeic.fragment.HomeFragment;
import nvduy1997.com.easytoeic.fragment.ListTestFragment;
import nvduy1997.com.easytoeic.fragment.ListeningFragment;
import nvduy1997.com.easytoeic.fragment.ReadingFragment;
import nvduy1997.com.easytoeic.fragment.TopicVocabularyFragment;

import nvduy1997.com.easytoeic.fragment.VOAFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , ReadingFragment.OnClickOpenFragment
        , GrammarFragment.OpenFragmentDetail
        , HomeFragment.OpenRead
        , HomeFragment.OpenDic
        , HomeFragment.OpenVOA
        , HomeFragment.OpenGrammar {

    private ReadingFragment readingFragment;
    private ListTestFragment listTestFragment;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,homeFragment);
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
            TopicVocabularyFragment topicVocabularyFragment = new TopicVocabularyFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, topicVocabularyFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.mini_test) {

        } else if (id == R.id.listen_engish) {
            ListeningFragment listeningFragment = new ListeningFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, listeningFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.readding_engish) {
            readingFragment = new ReadingFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, readingFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.grammar_english) {
            GrammarFragment grammarFragment = new GrammarFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, grammarFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.dictionary) {

        } else if (id == R.id.voa) {
            VOAFragment voaFragment = new VOAFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, voaFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.login) {

        } else if (id == R.id.share) {

        } else if (id == R.id.about_me) {

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClickOpenFragment() {
        if (listTestFragment == null) {
            listTestFragment = new ListTestFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listTestFragment).addToBackStack(null).commit();

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

    }

    @Override
    public void openVOA() {
        VOAFragment voaFragment = new VOAFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, voaFragment);
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

}
