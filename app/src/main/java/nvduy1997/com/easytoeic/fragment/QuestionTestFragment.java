package nvduy1997.com.easytoeic.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.Database.Dapan;
import nvduy1997.com.easytoeic.Database.DatabasManager;
import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Question;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTestFragment extends Fragment {


    private View view;
    private static final String ARG_PAGE = "page";
    private static final String ARG_CHECK = "check_answer";
    int mPagerNumber; // vitri trang hien tai
    TextView tvNum, tvQ;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    private ArrayList<Question> array;
    private ArrayList<Dapan> arrayDapan = new ArrayList<>();
    DatabasManager databasManager;
    public int checkAns;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databasManager = new DatabasManager(getContext());
        arrayDapan = databasManager.getAllDapAn();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_screen_slide, container, false);
        assert getArguments() != null;
        mPagerNumber = getArguments().getInt(ARG_PAGE);
        checkAns = getArguments().getInt(ARG_CHECK);
        init();
        getDataQuestion();
        return view;

    }

    private void init() {
        tvNum = view.findViewById(R.id.tvNum);
        tvQ = view.findViewById(R.id.tvQuestion);
        radioGroup = view.findViewById(R.id.radGroup);
        radA = view.findViewById(R.id.radA);
        radB = view.findViewById(R.id.radB);
        radC = view.findViewById(R.id.radC);
        radD = view.findViewById(R.id.radD);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public static QuestionTestFragment create(int pageNumber, int checkAnswer) {
        QuestionTestFragment frg = new QuestionTestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, pageNumber);
        bundle.putInt(ARG_CHECK,checkAnswer);
        frg.setArguments(bundle);
        return frg;
    }

    private void getDataQuestion(){
        DataService dataService = APIService.getService();
        Call<List<Question>> callback = dataService.getQuestionP5Test1();
        callback.enqueue(new Callback<List<Question>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                array = (ArrayList<Question>) response.body();
                tvNum.setText("Câu "+ (mPagerNumber + 1));
                tvQ.setText(array.get(mPagerNumber).getTen());
                radA.setText(array.get(mPagerNumber).getA());
                radB.setText(array.get(mPagerNumber).getB());
                radC.setText(array.get(mPagerNumber).getC());
                radD.setText(array.get(mPagerNumber).getD());

                if (checkAns != 0){
                    radA.setClickable(false);
                    radB.setClickable(false);
                    radC.setClickable(false);
                    radD.setClickable(false);
                    CheckAnswer(arrayDapan.get(mPagerNumber).getKetQua());
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        array.get(mPagerNumber).choiceID = checkedId;
//                        array.get(mPagerNumber).setTraloi(getChoiceFromID(checkedId));
//                        Toast.makeText(getContext(),array.get(mPagerNumber).getTraloi(),Toast.LENGTH_SHORT).show();
                        DatabasManager databasManager = new DatabasManager(getContext());
                        Dapan dapan = new Dapan();
                        dapan.setCauHoi((mPagerNumber+1));
                        dapan.setDa(getChoiceFromID(checkedId));
                        dapan.setKetQua(array.get((mPagerNumber)).getCheck());
                        Log.e("onCheckedChanged", "onCheckedChanged: " + array.get((mPagerNumber)).getCheck());
                     //   databasManager.addDA(dapan);
                       databasManager.UpdateDa(dapan);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });
    }
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

    // Ham kiem tra cac cau dung
    private void CheckAnswer(String ans){
        if (ans.equals("A")){
            radA.setBackgroundColor(Color.BLUE);
        }
        else if (ans.equals("B")){
            radB.setBackgroundColor(Color.BLUE);
        }
        else if (ans.equals("C")){
            radC.setBackgroundColor(Color.BLUE);
        }
        else if (ans.equals("D")){
            radD.setBackgroundColor(Color.BLUE);
        }
    }


}
