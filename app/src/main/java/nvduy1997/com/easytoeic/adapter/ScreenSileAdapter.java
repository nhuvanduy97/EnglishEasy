package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Question;


public class ScreenSileAdapter extends RecyclerView.Adapter<ScreenSileAdapter.viewHolder> {

    private ArrayList<Question> listQuestion;
    Context context;

    public ScreenSileAdapter(Context context, ArrayList<Question> listQuestion){
        this.context = context;
        this.listQuestion = listQuestion;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_screen_slide, viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Question question = listQuestion.get(i);
        viewHolder.tvQuestion.setText(question.getTen());
        viewHolder.numberQuestion.setText(question.getId());
        viewHolder.radA.setText(question.getA());
        viewHolder.radB.setText(question.getB());
        viewHolder.radC.setText(question.getC());
        viewHolder.radD.setText(question.getD());
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion,numberQuestion;
        RadioGroup radioGroup;
        RadioButton radA;
        RadioButton radB;
        RadioButton radC;
        RadioButton radD;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            numberQuestion = itemView.findViewById(R.id.numberQuestion);
            radioGroup = itemView.findViewById(R.id.radGroup);
            radA = itemView.findViewById(R.id.radA);
            radB = itemView.findViewById(R.id.radB);
            radC = itemView.findViewById(R.id.radC);
            radD = itemView.findViewById(R.id.radD);
        }
    }
}
