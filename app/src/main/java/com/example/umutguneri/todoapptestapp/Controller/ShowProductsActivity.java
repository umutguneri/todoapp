package com.example.umutguneri.todoapptestapp.Controller;

import com.example.umutguneri.todoapptestapp.R;
import com.example.umutguneri.todoapptestapp.Service.HttpHandler;
import com.example.umutguneri.todoapptestapp.Model.Product;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class ShowProductsActivity extends AppCompatActivity  {

    private String TAG = MainActivity.class.getSimpleName();
    public static ArrayList<String> productList_String;
    public static Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productList_String = new ArrayList<>();
        product = new Product();
        setContentView(R.layout.activity_show_products);

        new GetProductInfo().execute();  //Fetching json file and building Listview

    }

    @SuppressLint("StaticFieldLeak")
    private class GetProductInfo extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Json Data is downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://api.myjson.com/bins/n992y";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray products = jsonObj.getJSONArray("arrayOfProducts");

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        product.setTitle(c.getString("title"));
                        product.setDescription(c.getString("description"));
                        product.setPrice(c.getString("price"));

                        productList_String.add(product.toString());
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ArrayAdapter<String> itemsAdapter =
                    new ArrayAdapter<>(Objects.requireNonNull(getApplicationContext()), R.layout.product_listview, R.id.textView, productList_String);
            ListView listView = (ListView) findViewById(R.id.product_list);
            listView.setAdapter(itemsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), productList_String.get(position), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }



}
