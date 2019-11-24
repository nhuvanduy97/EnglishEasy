package nvduy1997.com.easytoeic.fragment;

import android.graphics.Color;
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

        if (checkAns != 0) {
            radAPart5.setClickable(false);
            radBPart5.setClickable(false);
            radCPart5.setClickable(false);
            radDPart5.setClickable(false);
            getCheckAns(arr_Ques.get(mPageNumber).getResultQuestion().toString());
        }

        radGroupPart5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                arr_Ques.get(mPageNumber).choiceID = checkedId;
                arr_Ques.get(mPageNumber).setTraLoi(getChoiceFromID(checkedId));
            }
        });
    }

    // Hàm kiểm tra câu đúng :

    private void getCheckAns(String ans) {
        if (ans.equals("A") == true) {
            radAPart5.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("B") == true) {
            radBPart5.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("C") == true) {
            radCPart5.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("D") == true) {
            radDPart5.setBackgroundColor(Color.GREEN);
        } else ;
    }

    // Lấy giá trị của radioGroup chuyển thành đáp án A,B,C,D

    private String getChoiceFromID(int ID) {
        if (ID == R.id.radAPart5) {
            return "A";
        } else if (ID == R.id.radBPart5) {
            return "B";
        } else if (ID == R.id.radCPart5) {
            return "C";
        } else if (ID == R.id.radDPart5) {
            return "D";
        } else {
            return "";
        }
    }

}
