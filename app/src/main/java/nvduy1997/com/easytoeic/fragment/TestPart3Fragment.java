package nvduy1997.com.easytoeic.fragment;

import android.content.Intent;
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
import nvduy1997.com.easytoeic.activity.Part3SlideActivity;
import nvduy1997.com.easytoeic.adapter.TestPart3Adapter;
import nvduy1997.com.easytoeic.model.TestPart3;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestPart3Fragment extends Fragment {
    private ListView listView;
    private TestPart3Adapter testPart3Adapter;
    private ArrayList<TestPart3> testPart3ArrayList;
    private ImageView btnSearchTestPart3;
    private EditText edtSearchTestPart3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_part3, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Part 3");
        listView = view.findViewById(R.id.lvTestPart3);
        testPart3ArrayList = new ArrayList<>();

        getData();

        btnSearchTestPart3 = view.findViewById(R.id.btnVoiceTestPart3);
        edtSearchTestPart3 = view.findViewById(R.id.edtSearchTestPart3);
        edtSearchTestPart3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // filter(s.toString());
            }
        });

        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<TestPart3>> callback = dataService.getTestPart3();
        callback.enqueue(new Callback<List<TestPart3>>() {
            @Override
            public void onResponse(Call<List<TestPart3>> call, Response<List<TestPart3>> response) {
                testPart3ArrayList = (ArrayList<TestPart3>) response.body();
                Log.d("PART3", "list : " + testPart3ArrayList.size());

                testPart3Adapter = new TestPart3Adapter(getActivity(), R.layout.list_test_part3, testPart3ArrayList);
                listView.setAdapter(testPart3Adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), Part3SlideActivity.class);
                        intent.putExtra("testPart3", testPart3ArrayList.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<TestPart3>> call, Throwable t) {

            }
        });
    }

    private void filter(String text) {
        ArrayList<TestPart3> filteredList = new ArrayList<>();
        for (TestPart3 item : testPart3ArrayList) {
            if (item.getTenTest().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        testPart3Adapter.filterList(filteredList);
    }
}
