package nvduy1997.com.easytoeic.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Test;

public class ListTestPart5Adapter extends RecyclerView.Adapter<ListTestPart5Adapter.viewHolder> implements View.OnClickListener {
    private LayoutInflater inflater;
    private ArrayList<Test> arrayTest;
    public OnClickItemView onClickItemView;

    public ListTestPart5Adapter(Context context, ArrayList<Test> tests) {
        this.arrayTest = tests;
        inflater = LayoutInflater.from(context);
    }

    public void setOnClickItemView(OnClickItemView onClickItemView) {
        this.onClickItemView = onClickItemView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_recycler_view, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Test test = arrayTest.get(i);
        viewHolder.textViewTest.setText(test.getTen());
        viewHolder.itemView.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return arrayTest.size();
    }

    @Override
    public void onClick(View v) {
        onClickItemView.onClickItemView();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView textViewTest;
        ImageView imageViewTest;

        @SuppressLint("CutPasteId")
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTest = itemView.findViewById(R.id.textViewTest);
            imageViewTest = itemView.findViewById(R.id.imgIcon);
        }
    }

    public interface OnClickItemView {
        void onClickItemView();
    }

}
