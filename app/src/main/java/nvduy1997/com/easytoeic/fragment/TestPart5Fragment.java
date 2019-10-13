package nvduy1997.com.easytoeic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import nvduy1997.com.easytoeic.adapter.TestPart5Adapter;
import nvduy1997.com.easytoeic.model.TestPart5;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestPart5Fragment extends Fragment {
    private ListView listView;
    private TestPart5Adapter testPart5Adapter;
    private ArrayList<TestPart5> testPart5ArrayList;
    private ImageView btnSearchTestPart5;
    private EditText edtSearchTestPart5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_part5, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Part 5");
        listView = view.findViewById(R.id.lvTestPart5);
        testPart5ArrayList = new ArrayList<>();
        getData();

        btnSearchTestPart5 = view.findViewById(R.id.btnVoiceTestPart5);
        edtSearchTestPart5 = view.findViewById(R.id.edtSearchTestPart5);
        edtSearchTestPart5.addTextChangedListener(new TextWatcher() {
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

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<TestPart5>> callback = dataService.getTestPart5();
        callback.enqueue(new Callback<List<TestPart5>>() {
            @Override
            public void onResponse(Call<List<TestPart5>> call, Response<List<TestPart5>> response) {
                testPart5ArrayList = (ArrayList<TestPart5>) response.body();
                Log.d("Test part 5", "onResponse: Test part 5" +testPart5ArrayList.size());
                testPart5Adapter = new TestPart5Adapter(getActivity(), R.layout.list_test_part5, testPart5ArrayList);
                listView.setAdapter(testPart5Adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<TestPart5>> call, Throwable t) {

            }
        });
    }

    private void filter(String text) {
        ArrayList<TestPart5> filteredList = new ArrayList<>();
        for (TestPart5 item : testPart5ArrayList) {
            if (item.getTenTest().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        testPart5Adapter.filterList(filteredList);
    }
}
