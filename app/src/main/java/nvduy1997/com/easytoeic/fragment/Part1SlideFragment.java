package nvduy1997.com.easytoeic.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.Part1SlideActivity;
import nvduy1997.com.easytoeic.model.QuestionPart1;

public class Part1SlideFragment extends Fragment {
    private static final String ARGUMENT_QUESTIONS = "ARGUMENT_QUESTIONS";
    ArrayList<QuestionPart1> arr_Question;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    private int mPageNumber; // Vị trí trang hiện tại
    private int checkAns; // biến kiểm tra

    private TextView txtNum;
    private ImageView imgQues;
    private RadioGroup radGroupPart1;
    private RadioButton radA, radB, radC, radD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_part1_slide, container, false);

        txtNum = view.findViewById(R.id.txtNum);
        imgQues = view.findViewById(R.id.imgQues);
        radGroupPart1 = view.findViewById(R.id.radGroupPart1);
        radA = view.findViewById(R.id.radA);
        radB = view.findViewById(R.id.radB);
        radC = view.findViewById(R.id.radC);
        radD = view.findViewById(R.id.radD);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_Question = new ArrayList<>();
        Part1SlideActivity part1SlideActivity = (Part1SlideActivity) getActivity();
        if (getArguments() != null) {
            arr_Question = getArguments().getParcelableArrayList(ARGUMENT_QUESTIONS);
            mPageNumber = getArguments().getInt(ARG_PAGE);
            checkAns = getArguments().getInt(ARG_CHECKANSWER);
        }

    }


    public static Part1SlideFragment create(int pageNumber, ArrayList<QuestionPart1> questionPart1s, int checkAnswer) {

        Part1SlideFragment slideFragment = new Part1SlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, pageNumber);
        bundle.putInt(ARG_CHECKANSWER, checkAnswer);
        bundle.putParcelableArrayList(ARGUMENT_QUESTIONS, questionPart1s);
        slideFragment.setArguments(bundle);
        return slideFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtNum.setText("Question " + (mPageNumber + 1));
        Picasso.with(getActivity()).load(arr_Question.get(mPageNumber).getHinhQuestion()).into(imgQues);
        radA.setText(arr_Question.get(mPageNumber).getDaA());
        radB.setText(arr_Question.get(mPageNumber).getDaB());
        radC.setText(arr_Question.get(mPageNumber).getDaC());
        radD.setText(arr_Question.get(mPageNumber).getDaD());

        if (checkAns != 0) {
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(arr_Question.get(mPageNumber).getResultQuestion().toString());
        }

        radGroupPart1.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Toast.makeText(getActivity(), "You choosed " + checkedId, Toast.LENGTH_SHORT).show();
                arr_Question.get(mPageNumber).choiceID = checkedId;
                arr_Question.get(mPageNumber).setTraLoi(getChoiceFromID(checkedId));
            }
        });

    }
    // Lấy giá trị của radioGroup chuyển thành đáp án A,B,C,D

    private String getChoiceFromID(int ID) {
        if (ID == R.id.radA) {
            return "A";
        } else if (ID == R.id.radB) {
            return "B";
        } else if (ID == R.id.radC) {
            return "C";
        } else if (ID == R.id.radD) {
            return "D";
        } else {
            return "";
        }
    }

    // Hàm kiểm tra câu đúng :

    private void getCheckAns(String ans) {
        if (ans.equals("A") == true) {
            radA.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("B") == true) {
            radB.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("C") == true) {
            radC.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("D") == true) {
            radD.setBackgroundColor(Color.GREEN);
        } else ;
    }

}
