package nvduy1997.com.easytoeic.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.MainActivity;
import nvduy1997.com.easytoeic.adapter.VocabularyAdapter;
import nvduy1997.com.easytoeic.model.Vocabulary;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VocabularyFragment extends Fragment {
    private RecyclerView recyclerView;
    private VocabularyAdapter vocabularyAdapter;
    private ArrayList<Vocabulary> vocabularyArrayList;
    private EditText edtSearchVocabulary;
    private TextToSpeech textToSpeech;
    private String voice;
    private ImageButton btnSettingVoice;
    private SeekBar sbPitch, sbSpeed;
    public float pitch, speed;
    private Button btnSetDiglog;
    private ImageView btnCloseDiglog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Vocabulary");
        recyclerView = view.findViewById(R.id.lvVocabulary);

        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != textToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);

                }
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            String idChuDe = bundle.getString("idTopic");
            getData(idChuDe);
        }

        // click search item

        edtSearchVocabulary = view.findViewById(R.id.edtSearchVocabulary);
        edtSearchVocabulary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

        });

        // click setting voice

        btnSettingVoice = view.findViewById(R.id.btnSetingVoice);
        btnSettingVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.diglog_custom);
                dialog.setTitle("Setting Voice");

                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) getContext()).getWindowManager()
                        .getDefaultDisplay()
                        .getMetrics(displayMetrics);
                int height = (int) (displayMetrics.heightPixels * 0.6f);
                int width = (int) (displayMetrics.widthPixels * 0.9f);
                dialog.getWindow().setLayout(width, height);

                btnCloseDiglog = dialog.findViewById(R.id.btnCloseDiglog);
                btnSetDiglog = dialog.findViewById(R.id.btnSetDiglog);
                sbPitch = dialog.findViewById(R.id.sbPitch);
                sbSpeed = dialog.findViewById(R.id.sbSpeed);

                btnCloseDiglog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnSetDiglog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pitch = (float) (sbPitch.getProgress() / 50);
                        if (pitch < 0.1) {
                            pitch = 0.1f;
                        }
                        speed = (float) (sbSpeed.getProgress() / 50);
                        if (speed < 0.1) {
                            speed = 0.1f;
                        }
                        dialog.dismiss();
                    }

                });
                dialog.show();
            }
        });

        return view;
    }

    public void getData(String idChuDe) {
        DataService dataService = APIService.getService();
        Call<List<Vocabulary>> callback = dataService.getVocabulary(idChuDe);
        callback.enqueue(new Callback<List<Vocabulary>>() {
            @Override
            public void onResponse(Call<List<Vocabulary>> call, Response<List<Vocabulary>> response) {
                vocabularyArrayList = (ArrayList<Vocabulary>) response.body();
                vocabularyAdapter = new VocabularyAdapter(getActivity(), vocabularyArrayList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(vocabularyAdapter);

                vocabularyAdapter.setOnNoteListener(new VocabularyAdapter.OnNoteListener() {
                    @Override
                    public void onNoteClick(int position) {

                        voice = vocabularyArrayList.get(position).getTuenglish();
                        textToSpeech.setPitch(pitch);
                        textToSpeech.setSpeechRate(speed);
                        textToSpeech.speak(voice, TextToSpeech.QUEUE_FLUSH, null, null);

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Vocabulary>> call, Throwable t) {

            }
        });
    }

    private void filter(String text) {
        ArrayList<Vocabulary> filteredList = new ArrayList<>();
        for (Vocabulary item : vocabularyArrayList) {
            if (item.getTuenglish().toLowerCase().contains(text.toLowerCase()) ||
                    item.getTuvietnam().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        vocabularyAdapter.filterList(filteredList);
    }

    @Override
    public void onDestroyView() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroyView();
    }
}
