package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Grammar;
import nvduy1997.com.easytoeic.model.TopicVocabulary;


public class GrammarAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Grammar> grammars;

    public GrammarAdapter(Context context, int layout, ArrayList<Grammar> grammars) {
        this.context = context;
        this.layout = layout;
        this.grammars = grammars;
    }

    @Override
    public int getCount() {
        return grammars.size();
    }

    @Override
    public Object getItem(int position) {
        return grammars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        ImageView idImgViewGrammar;
        TextView textViewBai;
        TextView textViewGrammar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.idImgViewGrammar = convertView.findViewById(R.id.idImgViewGrammar);
            viewHolder.textViewBai = convertView.findViewById(R.id.textViewBai);
            viewHolder.textViewGrammar = convertView.findViewById(R.id.textViewGrammar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Grammar grammar = grammars.get(position);
        viewHolder.textViewBai.setText(grammar.getId());
        viewHolder.textViewGrammar.setText(grammar.getTen());
        Picasso.with(context).load(grammar.getHinhAnh()).into(viewHolder.idImgViewGrammar);


        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        convertView.startAnimation(animation);

        return convertView;
    }

    public void filterList(ArrayList<Grammar> filteredList) {
        grammars = filteredList;
        notifyDataSetChanged();
    }
}
