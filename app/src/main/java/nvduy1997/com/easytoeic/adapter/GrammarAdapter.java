package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Grammar;


public class GrammarAdapter extends BaseAdapter {

    private ArrayList<Grammar> grammars;
    private Context context;

    public GrammarAdapter(ArrayList<Grammar> grammars, Context context) {
        this.grammars = grammars;
        this.context = context;
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

    public class ViewHolder{
        ImageView idImgViewGrammar;
        TextView textViewBai;
        TextView textViewGrammar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_grammar,null);
            viewHolder.idImgViewGrammar = convertView.findViewById(R.id.idImgViewGrammar);
            viewHolder.textViewBai = convertView.findViewById(R.id.textViewBai);
            viewHolder.textViewGrammar = convertView.findViewById(R.id.textViewGrammar);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Grammar grammar = grammars.get(position);
        viewHolder.textViewBai.setText(grammar.getId());
        viewHolder.textViewGrammar.setText(grammar.getTen());
        Picasso.with(context).load(grammar.getHinhAnh()).into(viewHolder.idImgViewGrammar);
        return convertView;
    }
}
