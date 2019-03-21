package nvduy1997.com.easytoeic.fragment;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
       ((MainActivity)getActivity()).getSupportActionBar().setTitle("Vocabulary");
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
        getVoice();
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

    public void getVoice() {

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
