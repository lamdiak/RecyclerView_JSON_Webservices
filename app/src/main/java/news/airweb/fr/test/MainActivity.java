package news.airweb.fr.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "description";

    private final String URL = "https://airweb-demo.airweb.fr/psg/psg.json";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Aiweb> MyList;
    //private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView)findViewById(R.id.card1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyList = new ArrayList<>();
        loadRecyclerViewdata();
    }

        public void loadRecyclerViewdata(){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Chargement en cours ...");
            progressDialog.show();


            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray array = jsonObject.getJSONArray("news");
                                for(int i =0; i<array.length();i++){
                                    JSONObject o = array.getJSONObject(i);
                                    Aiweb item = new Aiweb(
                                            o.getString("title"),
                                            o.getString("content"),
                                            o.getString("picture"));
                                    MyList.add(item);
                                }

                                adapter = new MyAdapter(MyList,getApplicationContext());
                                recyclerView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }


  /*  @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        Aiweb clickedItem = MyList.get(position);
        intent.putExtra(EXTRA_URL, clickedItem.getImage());
        intent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
        intent.putExtra(EXTRA_DESC, clickedItem.getShortdesc());

        startActivity(intent);
    }*/
}
