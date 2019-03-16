package nvduy1997.com.easytoeic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import nvduy1997.com.easytoeic.R;

public class HomeFragment extends Fragment {


    LinearLayout lnRead, lnDictionary, lnListen, lnVoa, lnGrammar, lnVocabulary;
    private View view;
    private OpenRead openRead;
    private OpenDic openDic;
    private OpenVOA voa;
    private OpenGrammar openGrammar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            openRead = (OpenRead) context;
            openDic = (OpenDic) context;
            voa = (OpenVOA) context;
            openGrammar = (OpenGrammar) context;
        } catch (Exception e) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
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
}
