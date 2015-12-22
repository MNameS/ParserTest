package com.example.parsertest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {


    public ArrayList<HashMap<String ,String>> mapArrayListOfHrefs;
    public ArrayList<ArrayList<HashMap<String , String>>> mapArrayListOfHrefsForSpecificProduct = new ArrayList<ArrayList<HashMap<String, String>>>();


    public ArrayList<String> titleList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView1);

        new NewThread().execute();

        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.item, titleList);
    }


    public class NewThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... arg) {

            mapArrayListOfHrefs = new ParserOfMainProductsGroup().getMapArrayListOfHrefs();
            Log.d("TAG", String.valueOf(mapArrayListOfHrefs));

            // parser works TODO save data in database to use application without internet connection.
            // TODO attach 4 activty (first activity contains two buttons: "create new receipe", "look my previous"  )
            // TODO second activity contains expandeblelistview with product groups and products in them
            // TODO 3-rd contains two listviews and layout with components for calculation(and buttons "save", "add product" ), one of them with data for 100 gm, another contains bars of macronutrients
            // TODO that calculate from data about product
            // TODO 4-th listviev of saved recipes
            // TODO 5-th recipe (information about products and amount of them we need to cover by daily demand in microelements)
            // TODO attach ui expandeblelist
            ArrayList<HashMap<String ,String>> temporaryInnerList;
            for (Map<String,String> m : mapArrayListOfHrefs){
                String t = m.entrySet().iterator().next().getKey();
                Log.d("Ref for product ", t);
               temporaryInnerList = new ParserForProductsInGroups(m.entrySet().iterator().next().getKey()).getMapArrayList();
                Log.d("TAG", String.valueOf(temporaryInnerList));
               mapArrayListOfHrefsForSpecificProduct.add(temporaryInnerList);
            }
            
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
}