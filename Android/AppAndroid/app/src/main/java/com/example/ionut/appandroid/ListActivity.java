package com.example.ionut.appandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> lista;
    String URL="http://195.127.0.2:8080/ServletAndroid/Server";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list);
        lista = getIntent().getStringArrayListExtra("list");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, lista);
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position : " + itemPosition + "  Item : " + itemValue, Toast.LENGTH_LONG)
                        .show();

                Map<String, String> postData = new HashMap<>();
                postData.put("userName", "Eus");
                postData.put("locationName", "dsa");
                HttpPostAsyncTask post=new HttpPostAsyncTask(postData);
                post.execute(URL);
            }

        });

    }

    public class HttpPostAsyncTask extends AsyncTask<String, Void, Void> {
        // This is the JSON body of the post
        JSONObject postData;
        // This is a constructor that allows you to pass in the JSON body
        public HttpPostAsyncTask(Map<String, String> postData) {
            if (postData != null) {
                this.postData = new JSONObject(postData);
            }
        }

        // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
        @Override
        protected Void doInBackground(String... params) {

            try {
                // This is getting the url from the string we passed in
                URL url = new URL(params[0]);

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestMethod("POST");
                // OPTIONAL - Sets an authorization header
                urlConnection.setRequestProperty("Authorization", "someAuthString");
                // Send the post body
                if (this.postData != null) {
                    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                    writer.write(postData.toString());
                    writer.flush();
                }

                int statusCode = urlConnection.getResponseCode();

                if (statusCode ==  200) {
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    System.out.println("Status Code:"+statusCode);
                    // From here you can convert the string to JSON with whatever JSON parser you like to use
                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
                } else {
                    // Status code is not 200
                    // Do something to handle the error
                    System.out.println("Status Code:"+statusCode);
                }

            } catch (Exception e) {

            }
            return null;
        }
    }
}
