package nvduy1997.com.easytoeic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.VideoPlayActivity;
import nvduy1997.com.easytoeic.adapter.VideoYoutubeAdapter;
import nvduy1997.com.easytoeic.model.VideoYoutube;

public class VOAFragment extends Fragment {


    public static String API_KEY = "AIzaSyCPk36hbaAfYCFvPonP4UWCCBlo_tCFlzw";
    String ID_PLAYLIST = "PLd9hCvj34W5hWkRym8sljiEvEBJ1JGIu5";
    String ID1 = "PLy3dgl5HqtbvR7X3qNRWfUIjm1JIyiKTA";
    String getJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + ID1 + "&key=" + API_KEY + "&maxResults=50";

    private ListView listView;
    private ArrayList<VideoYoutube> videoYoutubes;
    private VideoYoutubeAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voa, container, false);
        listView = view.findViewById(R.id.lvVideo);
        videoYoutubes = new ArrayList<>();
        adapter = new VideoYoutubeAdapter(getContext(), R.layout.list_video_youtube, videoYoutubes);
        listView.setAdapter(adapter);
        GetJsonYoutube(getJson);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getContext(), VideoPlayActivity.class);
                intent.putExtra("idVideoYoutube", videoYoutubes.get(position).getIdVideo());
                startActivity(intent);

            }
        });
        return view;
    }


    public void GetJsonYoutube(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonItems = response.getJSONArray("items");

                    String title = "";
                    String url = "";
                    String idVideo = "";

                    for (int i = 0; i < jsonItems.length(); i++) {

                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");
                        JSONObject jsonThumbnails = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThumbnails.getJSONObject("medium");
                        url = jsonMedium.getString("url");
                        JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                        idVideo = jsonResourceId.getString("videoId");
                        videoYoutubes.add(new VideoYoutube(title, url, idVideo));
                        Log.d("TAG", "onResponse: " + videoYoutubes.get(i).toString());

                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);

    }

}
