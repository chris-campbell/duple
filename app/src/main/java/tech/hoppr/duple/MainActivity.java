package tech.hoppr.duple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

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

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    String name = "https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&\n" +
            "exclude={part}&appid=17ebb4f754fbdd243a6291f9ade686eb\n";
    private ArrayList<DayWeather> weatherDataList = new ArrayList<>();
    private RequestQueue mqueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mqueue = Volley.newRequestQueue(this);
        parseJson();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.weather_menu, menu);
        return true;
    }

    private void parseJson() {
        String url = "https://api.openweathermap.org/data/2.5/forecast?zip=11213&appid=17ebb4f754fbdd243a6291f9ade686eb";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                double minTemp;
                double maxTemp;
                String description = null;
                try {
                    JSONArray jsonArray = response.getJSONArray("list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject listItem = jsonArray.getJSONObject(i);

                        JSONObject main = listItem.getJSONObject("main");
                        JSONArray weatherArray = listItem.getJSONArray("weather");


                        // MAIN JSON ITEMS
                        minTemp = main.getDouble("temp_min");
                        maxTemp = main.getDouble("temp_max");


                        // WEATHER JSON ITEMS
                        for (int j = 0; j < weatherArray.length(); j++) {
                            JSONObject weatherItem = weatherArray.getJSONObject(j);
                            description = weatherItem.getString("description");
                            Log.d("WEATHER DESC", description);
                        }
                        weatherDataList.add(new DayWeather(minTemp, maxTemp, description));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mqueue.add(request);

    }
}