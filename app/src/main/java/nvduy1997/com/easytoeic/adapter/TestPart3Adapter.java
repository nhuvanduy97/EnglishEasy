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

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.TestPart3;

public class TestPart3Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TestPart3> testPart3List;

    public TestPart3Adapter(Context context, int layout, List<TestPart3> testPart3List) {
        this.context = context;
        this.layout = layout;
        this.testPart3List = testPart3List;
    }

    @Override
    public int getCount() {
        return testPart3List.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.txtTestPart3 = convertView.findViewById(R.id.txtTestPart3);
            viewHolder.imgTestPart3 = convertView.findViewById(R.id.imgTestPart3);
            viewHolder.imgDowloadTestPart3 = convertView.findViewById(R.id.imgDowloadTestPart3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TestPart3 testPart3 = testPart3List.get(position);
        viewHolder.txtTestPart3.setText(testPart3.getTenTest());
        viewHolder.imgDowloadTestPart3.setImageResource(R.drawable.down);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        convertView.startAnimation(animation);


        return convertView;
    }

    private class ViewHolder {
        TextView txtTestPart3;
        ImageView imgTestPart3, imgDowloadTestPart3;
    }

    public void filterList(ArrayList<TestPart3> filteredList) {
        testPart3List = filteredList;
        notifyDataSetChanged();
    }
}
