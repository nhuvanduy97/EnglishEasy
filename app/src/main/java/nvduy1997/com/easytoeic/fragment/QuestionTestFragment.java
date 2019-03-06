package nvduy1997.com.easytoeic.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.QuestionTestAdapter;
import nvduy1997.com.easytoeic.model.Question;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTestFragment extends Fragment {

    public static final String TAG = "ID_TEST";
    private QuestionTestAdapter adapter;
    private RecyclerView recyclerViewQuestion;
    private View view;
    private int ID;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            ID = bundle.getInt(TAG);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_screen_slide, container, false);
        recyclerViewQuestion = view.findViewById(R.id.RecyclerViewQuestion);
        if (ID == 1) {
            getAllQuestionPart5Test1();
        }

        return view;

    }

    public void getAllQuestionPart5Test1() {
        DataService dataService = APIService.getService();
        Call<List<Question>> callBack = dataService.getQuestionP5Test1();
        callBack.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                recyclerViewQuestion = view.findViewById(R.id.RecyclerViewQuestion);
                ArrayList<Question> listQuestion = (ArrayList<Question>) response.body();
                adapter = new QuestionTestAdapter(getActivity(), listQuestion);
                LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                recyclerViewQuestion.setLayoutManager(linearLayout);
                recyclerViewQuestion.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
