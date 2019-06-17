package nvduy1997.com.easytoeic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.MainActivity;
import nvduy1997.com.easytoeic.adapter.DetailGrammarAdapter;
import nvduy1997.com.easytoeic.model.DetailGrammar;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailGrammarFragment extends Fragment {

    private View view;
    private ArrayList<DetailGrammar> detailGrammarArrayList;
    private ListView listViewDetail;
    private DetailGrammarAdapter adapter;
    private int idGrammar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            idGrammar = bundle.getInt("KEY");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_grammar, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Grammar");
        listViewDetail = view.findViewById(R.id.ListViewDetail);
        getDetailGrammar(String.valueOf(idGrammar));
        return view;
    }

    private void getDetailGrammar(String idGrammar) {
        DataService dataService = APIService.getService();
        Call<List<DetailGrammar>> callback = dataService.getDetailgrammar(idGrammar);
        callback.enqueue(new Callback<List<DetailGrammar>>() {
            @Override
            public void onResponse(Call<List<DetailGrammar>> call, Response<List<DetailGrammar>> response) {
                detailGrammarArrayList = (ArrayList<DetailGrammar>) response.body();
                adapter = new DetailGrammarAdapter(detailGrammarArrayList, getContext());
                listViewDetail.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DetailGrammar>> call, Throwable t) {

            }
        });
    }
}
