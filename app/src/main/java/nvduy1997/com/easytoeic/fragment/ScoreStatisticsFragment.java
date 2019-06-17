package nvduy1997.com.easytoeic.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.MainActivity;
import nvduy1997.com.easytoeic.adapter.ScoreStatisticAdapter;
import nvduy1997.com.easytoeic.manager.DatabaseManager;
import nvduy1997.com.easytoeic.model.Score;

public class ScoreStatisticsFragment extends Fragment {
    private ScoreStatisticAdapter statisticAdapter;
    private ListView lvScoreStatis;
    private DatabaseManager databaseManager;
    private List<Score> scoreList = new ArrayList<>();
    int a;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_statistics, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Score Statistic");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        databaseManager = new DatabaseManager(getActivity());
        lvScoreStatis = getActivity().findViewById(R.id.lvScoreStatistic);
        scoreList = databaseManager.getAllScore();
        statisticAdapter = new ScoreStatisticAdapter(getActivity(), R.layout.list_score_statistic, scoreList);
        lvScoreStatis.setAdapter(statisticAdapter);

        lvScoreStatis.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                a = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Warning!");
                builder.setIcon(R.drawable.exit);
                builder.setMessage("Do you delete this?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scoreList.remove(a);
                        statisticAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "History has been deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
            }
        });
    }
}
