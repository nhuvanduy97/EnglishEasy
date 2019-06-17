package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.Database.Dapan;
import nvduy1997.com.easytoeic.R;


public class CheckAnswerPart5Adapter extends BaseAdapter {

    ArrayList<Dapan> listDapan;
    LayoutInflater inflater;

    public CheckAnswerPart5Adapter(ArrayList<Dapan> listData, Context context) {
        this.listDapan = listData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return listDapan.size();
    }

    @Override
    public Object getItem(int position) {
        return listDapan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dapan dapan = (Dapan) getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_girdview_check, null);
            holder.txtNumAns = convertView.findViewById(R.id.txtNumAns);
            holder.txtAns = convertView.findViewById(R.id.txtAns);
            convertView.setTag(holder);
        } else {
            holder = (CheckAnswerPart5Adapter.ViewHolder) convertView.getTag();
        }

        int i = position + 1; // text câu hỏi
        holder.txtNumAns.setText(i + " : ");
        holder.txtAns.setText(dapan.getDa());

        return convertView;
    }
    public  class ViewHolder {
        TextView txtNumAns, txtAns;

    }

}
