package nvduy1997.com.easytoeic.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.MainActivity;

public class HomeFragment extends Fragment {


    LinearLayout lnRead, lnDictionary, lnListen, lnVoa, lnGrammar, lnVocabulary;
    private View view;
    private OpenRead openRead;
    private OpenDic openDic;
    private OpenVOA voa;
    private OpenGrammar openGrammar;
    private OpenListenning openListenning;
    private OpenVocabulary openVocabulary;

    private ImageView imgSplashHome, imgTree;
    private TextView txtSplash1, txtSplash2, txtSplash3;
    private Animation frombottom;
    private LinearLayout lvMenu;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            openRead = (OpenRead) context;
            openDic = (OpenDic) context;
            voa = (OpenVOA) context;
            openGrammar = (OpenGrammar) context;
            openListenning = (OpenListenning) context;
            openVocabulary = (OpenVocabulary) context;
        } catch (Exception e) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("ToeicTBD");

        imgSplashHome = view.findViewById(R.id.imgSplashHome);
        imgTree = view.findViewById(R.id.imgTree);
        txtSplash1 = view.findViewById(R.id.txtSplash1);
        txtSplash2 = view.findViewById(R.id.txtSplash2);
        txtSplash3 = view.findViewById(R.id.txtSplash3);
        lvMenu = view.findViewById(R.id.lvMenu);
        frombottom = AnimationUtils.loadAnimation(getActivity(), R.anim.frombottom);

        imgSplashHome.animate().translationY(-1600).setDuration(800).setStartDelay(300);
        imgTree.animate().alpha(2).setDuration(800).setStartDelay(600);
        txtSplash1.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(600);
        txtSplash2.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(600);
        txtSplash3.startAnimation(frombottom);
        lvMenu.startAnimation(frombottom);
        lnRead = view.findViewById(R.id.linear_readding);
        lnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRead.openRead();
            }
        });
        lnDictionary = view.findViewById(R.id.linear_dictionary);
        lnDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDic.openDic();
            }
        });
        lnListen = view.findViewById(R.id.linear_listen_english);
        lnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListenning.openListenning();
            }
        });
        lnVoa = view.findViewById(R.id.linear_voa_english);
        lnVoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voa.openVOA();
            }
        });
        lnGrammar = view.findViewById(R.id.linear_grammar_english);
        lnGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGrammar.openGrammar();
            }
        });
        lnVocabulary = view.findViewById(R.id.linear_vocabulary_english);
        lnVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVocabulary.openVocabulary();
            }
        });
        return view;
    }

    public interface OpenRead {
        void openRead();
    }

    public interface OpenDic {
        void openDic();
    }

    public interface OpenVOA {
        void openVOA();
    }

    public interface OpenGrammar {
        void openGrammar();
    }

    public interface OpenListenning {
        void openListenning();
    }

    public interface OpenVocabulary {
        void openVocabulary();
    }

    public void chooseAlerDiglog() {
        AlertDialog.Builder alertDiglog = new AlertDialog.Builder(getActivity());
        alertDiglog.setTitle("Warning");
        alertDiglog.setIcon(R.drawable.nowifi);
        alertDiglog.setMessage("You are not connected to the internet!Do you want to turn on wifi to continue");

        alertDiglog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //wifiManager.setWifiEnabled(true);
                openListenning.openListenning();
            }
        });

        alertDiglog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDiglog.show();
    }
}
