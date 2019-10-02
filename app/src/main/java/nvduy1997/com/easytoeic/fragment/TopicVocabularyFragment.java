package nvduy1997.com.easytoeic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.MainActivity;
import nvduy1997.com.easytoeic.adapter.TopicVocabularyAdapter;
import nvduy1997.com.easytoeic.model.TopicVocabulary;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicVocabularyFragment extends Fragment {

    private ListView listView;
    private ArrayList<TopicVocabulary> topicVocabularyArrayList;
    private TopicVocabularyAdapter adapter;
    private ImageView btnVoiceTopic;
    private EditText edtSearchTopic;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_vocabulary, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Topic Vocabulary");

        listView = view.findViewById(R.id.lvTopicVocabulary);
        topicVocabularyArrayList = new ArrayList<>();
        getData();

        btnVoiceTopic = view.findViewById(R.id.btnVoiceTopic);
        btnVoiceTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeedInput();
            }
        });

        edtSearchTopic = view.findViewById(R.id.edtSearchTopic);
        edtSearchTopic.addTextChangedListener(new TextWatcher() {
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

        return view;
    }

    public void getData() {
        DataService dataService = APIService.getService();
        Call<List<TopicVocabulary>> callback = dataService.getTopicVocabulary();
        callback.enqueue(new Callback<List<TopicVocabulary>>() {
            @Override
            public void onResponse(Call<List<TopicVocabulary>> call, Response<List<TopicVocabulary>> response) {
                topicVocabularyArrayList = (ArrayList<TopicVocabulary>) response.body();
                adapter = new TopicVocabularyAdapter(getActivity(), R.layout.list_topic_vocabulary, topicVocabularyArrayList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        VocabularyFragment vocabularyFragment = new VocabularyFragment();

                        Bundle bundle = new Bundle();
                        bundle.putString("idTopic", topicVocabularyArrayList.get(position).getIdchude());
                        vocabularyFragment.setArguments(bundle);

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, vocabularyFragment);
                        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<TopicVocabulary>> call, Throwable t) {

            }
        });
    }

    public void getSpeedInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        startActivityForResult(intent, 10);
    }

    private void filter(String text) {
        ArrayList<TopicVocabulary> filteredList = new ArrayList<>();
        for (TopicVocabulary item : topicVocabularyArrayList) {
            if (item.getTenchude().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
}
