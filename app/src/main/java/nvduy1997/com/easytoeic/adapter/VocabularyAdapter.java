package nvduy1997.com.easytoeic.adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Vocabulary;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Vocabulary> vocabularyArrayList;
    private OnNoteListener onNoteListener;

    public VocabularyAdapter(Context context, ArrayList<Vocabulary> vocabularyArrayList) {
        this.context = context;
        this.vocabularyArrayList = vocabularyArrayList;
    }

    public void setOnNoteListener(OnNoteListener listener) {
        onNoteListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_vocabulary, viewGroup, false);
        return new ViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Vocabulary vocabulary = vocabularyArrayList.get(i);
        Picasso.with(context).load(vocabulary.getHinhtuvung()).into(viewHolder.imgVocabulary);
        viewHolder.txtVidu.setText("Ví dụ : ");
        viewHolder.txtTuEN.setText(vocabulary.getTuenglish());
        viewHolder.txtTuVN.setText(vocabulary.getTuvietnam());
        viewHolder.txtVdEN.setText(vocabulary.getVdenglish());
        viewHolder.txtVdVN.setText(vocabulary.getVdvietnam());

    }

    @Override
    public int getItemCount() {
        return vocabularyArrayList.size();
    }

    public void filterList(ArrayList<Vocabulary> filteredList) {
        vocabularyArrayList = filteredList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgVocabulary;
        private TextView txtTuEN, txtTuVN, txtVidu, txtVdEN, txtVdVN;

        public ViewHolder(@NonNull View itemView, final OnNoteListener listener) {
            super(itemView);
            imgVocabulary = itemView.findViewById(R.id.imgVocabulary);
            txtTuEN = itemView.findViewById(R.id.txtTuEN);
            txtTuVN = itemView.findViewById(R.id.txtTuVN);
            txtVdEN = itemView.findViewById(R.id.txtVdEN);
            txtVdVN = itemView.findViewById(R.id.txtVdVN);
            txtVidu = itemView.findViewById(R.id.txtVidu);

            txtTuEN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onNoteClick(position);
                        }
                    }
                }
            });
        }

    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
