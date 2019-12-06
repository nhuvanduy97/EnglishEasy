package nvduy1997.com.easytoeic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.MainActivity;

public class ReadingFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reading, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Reading");
        initlizeComponents();
        return view;
    }

    private void initlizeComponents() {
        RelativeLayout relativeLayoutPart5 = view.findViewById(R.id.relativeLayout_Part5);
        relativeLayoutPart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestPart5Fragment testPart5Fragment = new TestPart5Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, testPart5Fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        RelativeLayout relativeLayoutPart6 = view.findViewById(R.id.relativeLayout_Part6);
        relativeLayoutPart6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RelativeLayout relativeLayoutPart7 = view.findViewById(R.id.relativeLayout_Part7);
        relativeLayoutPart7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestPart7Fragment testPart7Fragment = new TestPart7Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, testPart7Fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
