package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.TestPart1;

public class TestPart1Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TestPart1> testPart1List;

    public TestPart1Adapter(Context context, int layout, List<TestPart1> testPart1List) {
        this.context = context;
        this.layout = layout;
        this.testPart1List = testPart1List;
    }

    @Override
    public int getCount() {
        return testPart1List.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTestPart1;
        ImageView imgTestPart1, imgDowloadTestPart1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.txtTestPart1 = convertView.findViewById(R.id.txtTestPart1);
            viewHolder.imgTestPart1 = convertView.findViewById(R.id.imgTestPart1);
            viewHolder.imgDowloadTestPart1 = convertView.findViewById(R.id.imgDowloadTestPart1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TestPart1 testPart1 = testPart1List.get(position);
        viewHolder.txtTestPart1.setText(testPart1.getTenTest());
        //Picasso.with(context).load(testPart1.getHinhTest()).into(viewHolder.imgTestPart1);
        viewHolder.imgDowloadTestPart1.setImageResource(R.drawable.down);
        return convertView;
    }

    public void filterList(ArrayList<TestPart1> filteredList) {
        testPart1List = filteredList;
        notifyDataSetChanged();
    }
}
