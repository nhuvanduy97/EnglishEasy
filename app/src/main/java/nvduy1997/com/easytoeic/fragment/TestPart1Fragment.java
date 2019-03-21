package nvduy1997.com.easytoeic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.MainActivity;
import nvduy1997.com.easytoeic.adapter.TestPart1Adapter;
import nvduy1997.com.easytoeic.model.TestPart1;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestPart1Fragment extends Fragment {

    private ListView listView;
    private TestPart1Adapter testPart1Adapter;
    private ArrayList<TestPart1> testPart1ArrayList;
    private ImageView btnSearchTestPart1;
    private EditText edtSearchTestPart1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_part1, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Part 1");
        listView = view.findViewById(R.id.lvTestPart1);
        testPart1ArrayList = new ArrayList<>();
        getData();

        btnSearchTestPart1 = view.findViewById(R.id.btnVoiceTestPart1);
        edtSearchTestPart1 = view.findViewById(R.id.edtSearchTestPart1);
        edtSearchTestPart1.addTextChangedListener(new TextWatcher() {
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
        Call<List<TestPart1>> callback = dataService.getTestPart1();
        callback.enqueue(new Callback<List<TestPart1>>() {
            @Override
            public void onResponse(Call<List<TestPart1>> call, Response<List<TestPart1>> response) {
                testPart1ArrayList = (ArrayList<TestPart1>) response.body();
                testPart1Adapter = new TestPart1Adapter(getActivity(), R.layout.list_test_part1, testPart1ArrayList);
                listView.setAdapter(testPart1Adapter);
            }

            @Override
            public void onFailure(Call<List<TestPart1>> call, Throwable t) {

            }
        });
    }

    private void filter(String text) {
        ArrayList<TestPart1> filteredList = new ArrayList<>();
        for (TestPart1 item : testPart1ArrayList) {
            if (item.getTenTest().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        testPart1Adapter.filterList(filteredList);
    }
}
