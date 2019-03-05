package nvduy1997.com.easytoeic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import nvduy1997.com.easytoeic.R;

import nvduy1997.com.easytoeic.adapter.ListTestAdapter;
import nvduy1997.com.easytoeic.model.Test;

public class ListTestFragment extends Fragment implements ListTestAdapter.OnClickItemView {

    private View view;
    private ListTestAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Test> arrayTest;
    private OnClickOpenTestP5 onClickOpenTestP5;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickOpenTestP5) {
            try {
                onClickOpenTestP5 = (OnClickOpenTestP5) context;
            } catch (Exception e) {

            }
        }

    }

    @Override
    public void onDetach() {
        onClickOpenTestP5 = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_test_part5, container, false);
        recyclerView = view.findViewById(R.id.RecyclerViewTest);
        addTen();
        adapter = new ListTestAdapter(getActivity(), arrayTest);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        adapter.setOnClickItemView(this);
        return view;
    }

    public void addTen() {
        arrayTest = new ArrayList<>();
        arrayTest.add(new Test("Test 1", 1));
        arrayTest.add(new Test("Test 2", 2));
        arrayTest.add(new Test("Test 3", 3));
        arrayTest.add(new Test("Test 4", 4));
        arrayTest.add(new Test("Test 5", 5));
        arrayTest.add(new Test("Test 6", 6));
        arrayTest.add(new Test("Test 7", 7));
        arrayTest.add(new Test("Test 8", 8));
        arrayTest.add(new Test("Test 9", 9));
        arrayTest.add(new Test("Test 10", 10));
    }

    @Override
    public void onClickItemView(Test test) {
        if (test.getId() == 1) {
            adapter = new ListTestAdapter(getContext(), arrayTest);
            onClickOpenTestP5.onClickOpenPart5(test.getId());
        }
    }

    public interface OnClickOpenTestP5 {
        void onClickOpenPart5(int id);
    }


}
