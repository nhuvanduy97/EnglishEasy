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
import nvduy1997.com.easytoeic.model.QuestionPart7;

public class Part7SlideFragment extends Fragment {

    List<QuestionPart7> arr_Ques;
    public static final String ARG_PAGE_PART7 = "page";
    public static final String ARG_CHECKANSWER_PART7 = "checkAnswer";
    private static final String ARGUMENT_QUESTIONS_PART7 = "ARGUMENT_QUESTIONS_PART7";
    private int mPageNumber; // Vị trí trang hiện tại
    private int checkAns; // biến kiểm tra

    private TextView tvNumPart7, tvQuestionPart7;
    private RadioGroup radGroupPart7;
    private RadioButton radAPart7, radBPart7, radCPart7, radDPart7;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide, container, false);

        tvNumPart7 = view.findViewById(R.id.tvNumPart5);
        tvQuestionPart7 = view.findViewById(R.id.tvQuestionPart5);
        radGroupPart7 = view.findViewById(R.id.radGroupPart5);
        radAPart7 = view.findViewById(R.id.radAPart5);
        radBPart7 = view.findViewById(R.id.radBPart5);
        radCPart7 = view.findViewById(R.id.radCPart5);
        radDPart7 = view.findViewById(R.id.radDPart5);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arr_Ques = new ArrayList<QuestionPart7>();
        if (getArguments() != null) {
            arr_Ques = getArguments().getParcelableArrayList(ARGUMENT_QUESTIONS_PART7);
            mPageNumber = getArguments().getInt(ARG_PAGE_PART7);
            checkAns = getArguments().getInt(ARG_CHECKANSWER_PART7);
        }

    }

    public static Part7SlideFragment create(int pageNumber, ArrayList<QuestionPart7> questionPart7s, int checkAnswer) {
        Part7SlideFragment part7SlideFragment = new Part7SlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_PART7, pageNumber);
        bundle.putInt(ARG_CHECKANSWER_PART7, checkAnswer);
        bundle.putParcelableArrayList(ARGUMENT_QUESTIONS_PART7, questionPart7s);
        part7SlideFragment.setArguments(bundle);

        return part7SlideFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNumPart7.setText("Question " + (mPageNumber + 1));
        tvQuestionPart7.setText(arr_Ques.get(mPageNumber).getTenQuestion());
        radAPart7.setText(arr_Ques.get(mPageNumber).getDaA());
        radBPart7.setText(arr_Ques.get(mPageNumber).getDaB());
        radCPart7.setText(arr_Ques.get(mPageNumber).getDaC());
        radDPart7.setText(arr_Ques.get(mPageNumber).getDaD());

        if (checkAns != 0) {
            radAPart7.setClickable(false);
            radBPart7.setClickable(false);
            radCPart7.setClickable(false);
            radDPart7.setClickable(false);
            getCheckAns(arr_Ques.get(mPageNumber).getResultQuestion().toString());
        }

        radGroupPart7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
            radAPart7.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("B") == true) {
            radBPart7.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("C") == true) {
            radCPart7.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("D") == true) {
            radDPart7.setBackgroundColor(Color.GREEN);
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
