package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.VideoYoutube;

public class VideoYoutubeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<VideoYoutube> videoYoutubeList;

    public VideoYoutubeAdapter(Context context, int layout, List<VideoYoutube> videoYoutubeList) {
        this.context = context;
        this.layout = layout;
        this.videoYoutubeList = videoYoutubeList;
    }

    @Override
    public int getCount() {
        return videoYoutubeList.size();
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
        ImageView imgThumbnail;
        TextView txtTitleVideo;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imgThumbnail = convertView.findViewById(R.id.imgThumbnail);
            viewHolder.txtTitleVideo = convertView.findViewById(R.id.txtTitleVideo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        VideoYoutube videoYoutube = videoYoutubeList.get(position);
        viewHolder.txtTitleVideo.setText(videoYoutube.getTitle());
        Picasso.with(context).load(videoYoutube.getThumbnail()).into(viewHolder.imgThumbnail);

        return convertView;
    }
}
