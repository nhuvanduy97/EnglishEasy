package nvduy1997.com.easytoeic.fragment;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.MainActivity;
import nvduy1997.com.easytoeic.adapter.GrammarAdapter;
import nvduy1997.com.easytoeic.model.Grammar;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GrammarFragment extends Fragment {
    private ListView listView;
    private ArrayList<Grammar> grammarArrayList;
    private GrammarAdapter adapter;
    private ImageView btnVoiceTopic;
    private EditText edtSearchTopic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grammar, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Grammar Topic");

        listView = view.findViewById(R.id.lvTopicGrammar);
        grammarArrayList = new ArrayList<>();
        getData();
        btnVoiceTopic = view.findViewById(R.id.btnVoiceTopic);
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
        Call<List<Grammar>> callback = dataService.getAllGrammar();
        callback.enqueue(new Callback<List<Grammar>>() {
            @Override
            public void onResponse(Call<List<Grammar>> call, Response<List<Grammar>> response) {
                grammarArrayList = (ArrayList<Grammar>) response.body();
                adapter = new GrammarAdapter(getActivity(), R.layout.dong_grammar, grammarArrayList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        DetailGrammarFragment detailGrammarFragment = new DetailGrammarFragment();

                        Bundle bundle = new Bundle();
                        bundle.putString("idgrammar", grammarArrayList.get(position).getId());
                        detailGrammarFragment.setArguments(bundle);

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, detailGrammarFragment);
                        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Grammar>> call, Throwable t) {

            }
        });
    }

    private void filter(String text) {
        ArrayList<Grammar> filteredList = new ArrayList<>();
        for (Grammar item : grammarArrayList) {
            if (item.getTen().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
}
