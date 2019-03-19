package nvduy1997.com.easytoeic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.GrammarAdapter;
import nvduy1997.com.easytoeic.model.Grammar;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrammarFragment extends Fragment  {
    private View view;
    private ListView listViewGrammar;
    private GrammarAdapter adapter;
    private OpenFragmentDetail listen;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listen = (OpenFragmentDetail) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnClickOpenFragment");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_grammar,container,false);
        listViewGrammar = view.findViewById(R.id.ListViewGrammar);
        gettAllGrammar();
        return view;
    }


    private void gettAllGrammar(){
        DataService dataService = APIService.getService();
        Call<List<Grammar>> callBack = dataService.getAllGrammar();
        callBack.enqueue(new Callback<List<Grammar>>() {
            @Override
            public void onResponse(Call<List<Grammar>> call, Response<List<Grammar>> response) {
                final ArrayList<Grammar> arrayGrammr = (ArrayList<Grammar>) response.body();
                adapter = new GrammarAdapter(arrayGrammr,getContext());
                listViewGrammar.setAdapter(adapter);
                listViewGrammar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        listen.Open(Integer.parseInt(arrayGrammr.get(position).getId()));

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Grammar>> call, Throwable t) {

            }
        });
    }

    public interface OpenFragmentDetail{
        void Open(int id);
    }


}
