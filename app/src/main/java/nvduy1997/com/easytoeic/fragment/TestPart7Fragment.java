package nvduy1997.com.easytoeic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import nvduy1997.com.easytoeic.activity.Part7SlideActivity;
import nvduy1997.com.easytoeic.adapter.TestPart7Adapter;
import nvduy1997.com.easytoeic.model.TestPart7;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestPart7Fragment extends Fragment {

    private ListView listView;
    private TestPart7Adapter testPart7Adapter;
    private ArrayList<TestPart7> testPart7ArrayList;
    private ImageView btnSearchTestPart7;
    private EditText edtSearchTestPart7;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_part5, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Part 7");

        listView = view.findViewById(R.id.lvTestPart5);
        testPart7ArrayList = new ArrayList<>();
        getData();

        btnSearchTestPart7 = view.findViewById(R.id.btnVoiceTestPart5);
        edtSearchTestPart7 = view.findViewById(R.id.edtSearchTestPart5);
        edtSearchTestPart7.addTextChangedListener(new TextWatcher() {
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
        Call<List<TestPart7>> callback = dataService.getTestPart7();
        callback.enqueue(new Callback<List<TestPart7>>() {
            @Override
            public void onResponse(Call<List<TestPart7>> call, Response<List<TestPart7>> response) {
                testPart7ArrayList = (ArrayList<TestPart7>) response.body();
                testPart7Adapter = new TestPart7Adapter(getActivity(), R.layout.list_test_part5, testPart7ArrayList);
                listView.setAdapter(testPart7Adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), Part7SlideActivity.class);
                        intent.putExtra("testPart7", testPart7ArrayList.get(position));
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onFailure(Call<List<TestPart7>> call, Throwable t) {

            }
        });
    }

    private void filter(String text) {
        ArrayList<TestPart7> filteredList = new ArrayList<>();
        for (TestPart7 item : testPart7ArrayList) {
            if (item.getTenTest().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        testPart7Adapter.filterList(filteredList);
    }
}
