package nvduy1997.com.easytoeic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import nvduy1997.com.easytoeic.R;

public class ReadingFragment extends Fragment {

    View view;
    RelativeLayout relativeLayoutPart5,relativeLayoutPart6,relativeLayoutPart7;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reading,container,false);
        anhxa();
        return view;
    }
    private void anhxa() {
        relativeLayoutPart5 = view.findViewById(R.id.relativeLayout_Part5);
        relativeLayoutPart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"OK",Toast.LENGTH_SHORT).show();
            }
        });
        relativeLayoutPart6 = view.findViewById(R.id.relativeLayout_Part6);
        relativeLayoutPart6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"OK1",Toast.LENGTH_SHORT).show();
            }
        });
        relativeLayoutPart7 = view.findViewById(R.id.relativeLayout_Part7);
        relativeLayoutPart7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"OK2",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
