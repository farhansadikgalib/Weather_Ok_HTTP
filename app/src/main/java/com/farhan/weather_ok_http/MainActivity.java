package com.farhan.weather_ok_http;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView view_city,view_temp, view_desc;

    ImageView weather;

    EditText search;

    FloatingActionButton search_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_city = findViewById(R.id.town);
        view_temp = findViewById(R.id.temp);
        view_desc = findViewById(R.id.desc);

        view_city.setText("");
        view_temp.setText("");
        view_desc.setText("");



        weather = findViewById(R.id.weather_image);
        search = findViewById(R.id.search_edit);
        search_btn = findViewById(R.id.floating_search);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getRootView().getWindowToken(),0);
                api_key(String.valueOf(search.getText()));





            }
        });








    }

    private void api_key(final String City) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?q=" + City + "&appid=a6f41d947e0542a26580bcd5c3fb90ef&units=metric")
                .get().build();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {

            Response response = okHttpClient.newCall(request).execute();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {


                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);

                        JSONArray array = jsonObject.getJSONArray("weather");

                        JSONObject object = array.getJSONObject(0);

                        String description = object.getString("description");

                        String icon = object.getString("icon");

                       JSONObject templ = object.getJSONObject("main");

                      Double Temperature = templ.getDouble("temp");
                        String tempa = Math.round(Temperature)+"°C";



                        setText(view_city,City);
                       setText(view_temp,tempa);
                        setText(view_desc,description);
                        setImage(weather,icon);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        }catch (IOException e){
            e.printStackTrace();


        }
    }

        private void setText(final TextView text, final String value){



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });


        }

    private void setImage(final ImageView imageView, final String value) {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (value) {
                    case "01d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "01n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "02d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "02n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "03d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "03n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "04d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "04n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "09d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "09n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "10d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "10n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "11d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "11n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "13d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    case "13n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.wheather));

                }


            }

        });

    }


}



