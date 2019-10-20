package nvduy1997.com.easytoeic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.Part5SlideActivity;
import nvduy1997.com.easytoeic.model.QuestionPart5;

public class Part5SlideFragment extends Fragment {

    List<QuestionPart5> arr_Ques;
    public static final String ARG_PAGE_PART5 = "page";
    public static final String ARG_CHECKANSWER_PART5 = "checkAnswer";
    private static final String ARGUMENT_QUESTIONS_PART5 = "ARGUMENT_QUESTIONS_PART5";
    private int mPageNumber; // Vị trí trang hiện tại
    private int checkAns; // biến kiểm tra

    private TextView tvNumPart5, tvQuestionPart5;
    private RadioGroup radGroupPart5;
    private RadioButton radAPart5, radBPart5, radCPart5, radDPart5;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide, container, false);

        tvNumPart5 = view.findViewById(R.id.tvNumPart5);
        tvQuestionPart5 = view.findViewById(R.id.tvQuestionPart5);
        radGroupPart5 = view.findViewById(R.id.radGroupPart5);
        radAPart5 = view.findViewById(R.id.radAPart5);
        radBPart5 = view.findViewById(R.id.radBPart5);
        radCPart5 = view.findViewById(R.id.radCPart5);
        radDPart5 = view.findViewById(R.id.radDPart5);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_Ques = new ArrayList<QuestionPart5>();
        Part5SlideActivity part5SlideActivity = (Part5SlideActivity) getActivity();
        if (getArguments() != null) {
            arr_Ques = getArguments().getParcelableArrayList(ARGUMENT_QUESTIONS_PART5);
            mPageNumber = getArguments().getInt(ARG_PAGE_PART5);
            checkAns = getArguments().getInt(ARG_CHECKANSWER_PART5);
        }

    }

    public static Part5SlideFragment create(int pageNumber, ArrayList<QuestionPart5> questionPart5s, int checkAnswer) {
        Part5SlideFragment part5SlideFragment = new Part5SlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_PART5, pageNumber);
        bundle.putInt(ARG_CHECKANSWER_PART5, checkAnswer);
        bundle.putParcelableArrayList(ARGUMENT_QUESTIONS_PART5, questionPart5s);
        part5SlideFragment.setArguments(bundle);

        return part5SlideFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNumPart5.setText("Question " + (mPageNumber + 1));
        tvQuestionPart5.setText(arr_Ques.get(mPageNumber).getTenQuestion());
        radAPart5.setText(arr_Ques.get(mPageNumber).getDaA());
        radBPart5.setText(arr_Ques.get(mPageNumber).getDaB());
        radCPart5.setText(arr_Ques.get(mPageNumber).getDaC());
        radDPart5.setText(arr_Ques.get(mPageNumber).getDaD());


    }
}
