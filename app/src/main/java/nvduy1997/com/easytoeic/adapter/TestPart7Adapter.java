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
import nvduy1997.com.easytoeic.model.TestPart7;

public class TestPart7Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TestPart7> testPart7List;

    public TestPart7Adapter(Context context, int layout, List<TestPart7> testPart7List) {
        this.context = context;
        this.layout = layout;
        this.testPart7List = testPart7List;
    }

    @Override
    public int getCount() {
        return testPart7List.size();
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
            viewHolder.txtTestPart7 = convertView.findViewById(R.id.txtTestPart5);
            viewHolder.imgTestPart7 = convertView.findViewById(R.id.imgTestPart5);
            viewHolder.imgDowloadTestPart7 = convertView.findViewById(R.id.imgDowloadTestPart5);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TestPart7 testPart7 = testPart7List.get(position);
        viewHolder.txtTestPart7.setText(testPart7.getTenTest());
        viewHolder.imgDowloadTestPart7.setImageResource(R.drawable.down);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        convertView.startAnimation(animation);

        return convertView;
    }

    private class ViewHolder {
        TextView txtTestPart7;
        ImageView imgTestPart7, imgDowloadTestPart7;
    }

    public void filterList(ArrayList<TestPart7> filteredList) {
        testPart7List = filteredList;
        notifyDataSetChanged();
    }
}
