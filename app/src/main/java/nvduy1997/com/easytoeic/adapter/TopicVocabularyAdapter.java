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
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.TopicVocabulary;
import nvduy1997.com.easytoeic.model.Vocabulary;

public class TopicVocabularyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TopicVocabulary> topicVocabularyList;

    public TopicVocabularyAdapter(Context context, int layout, List<TopicVocabulary> topicVocabularyList) {
        this.context = context;
        this.layout = layout;
        this.topicVocabularyList = topicVocabularyList;
    }

    @Override
    public int getCount() {
        return topicVocabularyList.size();
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
        TextView txtTopic;
        ImageView imgTopic, imgDowloadTopic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imgTopic = convertView.findViewById(R.id.imgTopic);
            viewHolder.txtTopic = convertView.findViewById(R.id.txtTopic);
            viewHolder.imgDowloadTopic = convertView.findViewById(R.id.imgDowloadTopic);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TopicVocabulary topicVocabulary = topicVocabularyList.get(position);
        viewHolder.txtTopic.setText(topicVocabulary.getTenchude());
        Picasso.with(context).load(topicVocabulary.getHinhchude()).into(viewHolder.imgTopic);
        viewHolder.imgDowloadTopic.setImageResource(R.drawable.down);

        return convertView;
    }

    public void filterList(ArrayList<TopicVocabulary> filteredList) {
        topicVocabularyList = filteredList;
        notifyDataSetChanged();
    }
}
