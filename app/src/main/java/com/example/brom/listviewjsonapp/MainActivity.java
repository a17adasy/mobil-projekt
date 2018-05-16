package com.example.brom.listviewjsonapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> dressingNames = new ArrayList<String>();
    List<Dressing> allDressing = new ArrayList<Dressing>();
    ListView myListView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FetchData getJson = new FetchData();
        getJson.execute();

        myListView = (ListView)findViewById(R.id.my_list);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                Bundle info = new Bundle();
                String name = allDressing.get(pos).getName();
                String loc = allDressing.get(pos).getLoc();
                String comp = allDressing.get(pos).getCompany();
                int size = allDressing.get(pos).getSize();
                int cost = allDressing.get(pos).getCost();
                String auxdata = allDressing.get(pos).getAuxdata();


                info.putString("INFO_NAME", name);
                info.putString("INFO_LOC", loc);
                info.putString("INFO_COMP", comp);
                info.putInt("INFO_SIZE", size);
                info.putInt("INFO_COST", cost);
                info.putString("INFO_AUX", auxdata);

                intent.putExtras(info);
                myListView.getContext().startActivity(intent);
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.refresh) {
            allDressing.clear();
            new FetchData().execute();
            Toast refreshed = Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT);
            refreshed.show();

            return true;
        }

        else if(id == R.id.about) {

            Intent intent = new Intent(this,AboutActivity.class);
            this.startActivity(intent);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class FetchData extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/a17adasy/xml/data.json");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.

            try{
                JSONArray jArray = new JSONArray(o);

                for(int i = 0; i < jArray.length(); i++){
                    JSONObject dressing = jArray.getJSONObject(i);

                    String name = dressing.getString("name");
                    String location = dressing.getString("location");
                    String company = dressing.getString("company");
                    int size = dressing.getInt("size");
                    int cost = dressing.getInt("cost");
                    String auxdata = dressing.getString("auxdata");

                    allDressing.add(new Dressing(name, location, company, size, cost, auxdata));
                    dressingNames.add(name);
                }

            } catch (JSONException e) {
                Log.e("brom", "ERROR: " + e);
            }


            adapter = new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview, R.id.my_item_textview, dressingNames);
            myListView = (ListView)findViewById(R.id.my_list);
            myListView.setAdapter(adapter);

        }
    }
}

