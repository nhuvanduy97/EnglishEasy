package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.TestPart5;

public class TestPart5Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TestPart5> testPart5List;

    public TestPart5Adapter(Context context, int layout, List<TestPart5> testPart5List) {
        this.context = context;
        this.layout = layout;
        this.testPart5List = testPart5List;
    }

    @Override
    public int getCount() {
        return testPart5List.size();
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
        TextView txtTestPart5;
        ImageView imgTestPart5, imgDowloadTestPart5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.txtTestPart5 = convertView.findViewById(R.id.txtTestPart5);
            viewHolder.imgTestPart5 = convertView.findViewById(R.id.imgTestPart5);
            viewHolder.imgDowloadTestPart5 = convertView.findViewById(R.id.imgDowloadTestPart5);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TestPart5 testPart5 = testPart5List.get(position);
        viewHolder.txtTestPart5.setText(testPart5.getTenTest());
        viewHolder.imgDowloadTestPart5.setImageResource(R.drawable.down);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        convertView.startAnimation(animation);

        return convertView;
    }

    public void filterList(ArrayList<TestPart5> filteredList) {
        testPart5List = filteredList;
        notifyDataSetChanged();
    }
}
