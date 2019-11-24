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

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.QuestionPart3;

public class Part3SlideFragment extends Fragment {
    private static final String ARGUMENT_QUESTIONS_PART3 = "ARGUMENT_QUESTIONS";
    ArrayList<QuestionPart3> arr_Question;
    public static final String ARG_PAGE_PART3 = "page";
    public static final String ARG_CHECKANSWER_PART3 = "checkAnswer";
    private int mPageNumber; // Vị trí trang hiện tại
    private int checkAns; // biến kiểm tra

    private TextView tvNumPart3, tvQuestionPart3;
    private RadioGroup radGroupPart3;
    private RadioButton radAPart3, radBPart3, radCPart3, radDPart3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part3_slide, container, false);

        tvNumPart3 = view.findViewById(R.id.tvNumPart3);
        tvQuestionPart3 = view.findViewById(R.id.tvQuestionPart3);
        radGroupPart3 = view.findViewById(R.id.radGroupPart3);
        radAPart3 = view.findViewById(R.id.radAPart3);
        radBPart3 = view.findViewById(R.id.radBPart3);
        radCPart3 = view.findViewById(R.id.radCPart3);
        radDPart3 = view.findViewById(R.id.radDPart3);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_Question = new ArrayList<>();
        if (getArguments() != null) {
            arr_Question = getArguments().getParcelableArrayList(ARGUMENT_QUESTIONS_PART3);
            mPageNumber = getArguments().getInt(ARG_PAGE_PART3);
            checkAns = getArguments().getInt(ARG_CHECKANSWER_PART3);
        }
    }

    public static Part3SlideFragment create(int pageNumber,ArrayList<QuestionPart3> questionPart3s,int checkAnswer){
        Part3SlideFragment slideFragment = new Part3SlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_PART3, pageNumber);
        bundle.putInt(ARG_CHECKANSWER_PART3, checkAnswer);
        bundle.putParcelableArrayList(ARGUMENT_QUESTIONS_PART3, questionPart3s);
        slideFragment.setArguments(bundle);
        return slideFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNumPart3.setText("Question " + (mPageNumber + 1));
        tvQuestionPart3.setText(arr_Question.get(mPageNumber).getTenQuestion());
        radAPart3.setText(arr_Question.get(mPageNumber).getDaA());
        radBPart3.setText(arr_Question.get(mPageNumber).getDaB());
        radCPart3.setText(arr_Question.get(mPageNumber).getDaC());
        radDPart3.setText(arr_Question.get(mPageNumber).getDaD());

        if (checkAns != 0) {
            radAPart3.setClickable(false);
            radBPart3.setClickable(false);
            radCPart3.setClickable(false);
            radDPart3.setClickable(false);
            getCheckAns(arr_Question.get(mPageNumber).getResultQuestion().toString());
        }

        radGroupPart3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                arr_Question.get(mPageNumber).choiceID = checkedId;
                arr_Question.get(mPageNumber).setTraLoi(getChoiceFromID(checkedId));
            }
        });
    }

    // Hàm kiểm tra câu đúng :

    private void getCheckAns(String ans) {
        if (ans.equals("A") == true) {
            radAPart3.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("B") == true) {
            radBPart3.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("C") == true) {
            radCPart3.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("D") == true) {
            radDPart3.setBackgroundColor(Color.GREEN);
        } else ;
    }

    // Lấy giá trị của radioGroup chuyển thành đáp án A,B,C,D

    private String getChoiceFromID(int ID) {
        if (ID == R.id.radAPart3) {
            return "A";
        } else if (ID == R.id.radBPart3) {
            return "B";
        } else if (ID == R.id.radCPart3) {
            return "C";
        } else if (ID == R.id.radDPart3) {
            return "D";
        } else {
            return "";
        }
    }
}
