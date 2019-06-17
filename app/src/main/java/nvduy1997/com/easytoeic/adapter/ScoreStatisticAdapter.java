package nvduy1997.com.easytoeic.adapter;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Score;

public class ScoreStatisticAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Score> scoreList;

    public ScoreStatisticAdapter(Context context, int layout, List<Score> scoreList) {
        this.context = context;
        this.layout = layout;
        this.scoreList = scoreList;
    }

    @Override
    public int getCount() {
        return scoreList.size();
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
        TextView txtTestStatis, txtScoreStatis, txtDateStatis;
        ImageView imgScore;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.txtDateStatis = convertView.findViewById(R.id.txtDateStatis);
            viewHolder.txtScoreStatis = convertView.findViewById(R.id.txtScoreStatis);
            viewHolder.txtTestStatis = convertView.findViewById(R.id.txtTestStatis);
            viewHolder.imgScore = convertView.findViewById(R.id.imgScore);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Score score = scoreList.get(position);
        viewHolder.txtDateStatis.setText(score.getDate());
        viewHolder.txtTestStatis.setText(score.getPart() + " - " + score.getName());
        viewHolder.txtScoreStatis.setText("Score : " + score.getScore());
        viewHolder.imgScore.setImageResource(R.drawable.document);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        convertView.startAnimation(animation);
        return convertView;
    }
}
