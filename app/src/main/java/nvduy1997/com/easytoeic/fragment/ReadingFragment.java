package nvduy1997.com.easytoeic.fragment;

import android.content.Context;
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

public class ReadingFragment extends Fragment implements View.OnClickListener {

    private View view;
    private OnClickOpenFragment listen;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listen = (OnClickOpenFragment) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnClickOpenFragment");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reading,container,false);
        anhxa();
        return view;
    }
    private void anhxa() {
        RelativeLayout relativeLayoutPart5 = view.findViewById(R.id.relativeLayout_Part5);
        relativeLayoutPart5.setOnClickListener(this);
        RelativeLayout relativeLayoutPart6 = view.findViewById(R.id.relativeLayout_Part6);
        relativeLayoutPart6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"OK1",Toast.LENGTH_SHORT).show();
            }
        });
        RelativeLayout relativeLayoutPart7 = view.findViewById(R.id.relativeLayout_Part7);
        relativeLayoutPart7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"OK2",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        listen.onClickOpenFragment();
    }

    public interface OnClickOpenFragment{
        void onClickOpenFragment();
    }
}
