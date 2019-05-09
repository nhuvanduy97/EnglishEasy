package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.QuestionPart1;

public class CheckAnswerPart1Adapter extends BaseAdapter {

    ArrayList lsData;
    LayoutInflater layoutInflater;

    public CheckAnswerPart1Adapter(ArrayList lsData, Context context) {
        this.lsData = lsData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lsData.size();
    }

    @Override
    public Object getItem(int position) {
        return lsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuestionPart1 data = (QuestionPart1) getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_girdview_check, null);
            holder.txtNumAns = convertView.findViewById(R.id.txtNumAns);
            holder.txtAns = convertView.findViewById(R.id.txtAns);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int i = position + 1; // text câu hỏi
        holder.txtNumAns.setText(i + " : ");
        holder.txtAns.setText(data.getTraLoi());

        return convertView;
    }

    private static class ViewHolder {
        TextView txtNumAns, txtAns;

    }
}
