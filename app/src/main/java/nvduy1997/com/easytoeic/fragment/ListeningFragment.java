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
import nvduy1997.com.easytoeic.model.TestPart1;

public class ListeningFragment extends Fragment {

    private RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3, relativeLayout4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listening, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Listening English");
        relativeLayout1 = view.findViewById(R.id.relativeLayout_Part1);
        relativeLayout2 = view.findViewById(R.id.relativeLayout_Part2);
        relativeLayout3 = view.findViewById(R.id.relativeLayout_Part3);
        relativeLayout4 = view.findViewById(R.id.relativeLayout_Part4);

        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TestPart1Fragment testPart1Fragment = new TestPart1Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, testPart1Fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestPart3Fragment testPart3Fragment = new TestPart3Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, testPart3Fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        relativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

}
