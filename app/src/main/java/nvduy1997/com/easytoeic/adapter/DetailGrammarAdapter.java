package nvduy1997.com.easytoeic.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.DetailGrammar;

public class DetailGrammarAdapter extends BaseAdapter {

    private ArrayList<DetailGrammar> detailGrammars;
    private Context context;

    public DetailGrammarAdapter(ArrayList<DetailGrammar> detailGrammars, Context context) {
        this.detailGrammars = detailGrammars;
        this.context = context;
    }

    @Override
    public int getCount() {
        return detailGrammars.size();
    }

    @Override
    public Object getItem(int position) {
        return detailGrammars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView dinhnghia, cautruc, cachsd, vidu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_detail_grammar, null);
            viewHolder.dinhnghia = convertView.findViewById(R.id.dinhnghia);
            viewHolder.cautruc = convertView.findViewById(R.id.cautruc);
            viewHolder.cachsd = convertView.findViewById(R.id.cachsd);
            viewHolder.vidu = convertView.findViewById(R.id.vidu);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DetailGrammar detailGrammar = detailGrammars.get(position);
        viewHolder.dinhnghia.setText(detailGrammar.getDinhnghia());
        viewHolder.cachsd.setText(detailGrammar.getCachsh());
        viewHolder.cautruc.setText(detailGrammar.getCautruc());
        viewHolder.vidu.setText(detailGrammar.getVidu());

        return convertView;
    }
}
