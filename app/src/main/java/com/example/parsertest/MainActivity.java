package com.example.parsertest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

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



    public ArrayList<Map<String ,String>> mapArrayListOfHrefs;
    public ArrayList<ArrayList<Map<String , String>>> mapArrayListOfHrefsForSpecificProduct;


    public ArrayList<String> titleList = new ArrayList<String>();
   // private ArrayAdapter<String> adapter;
    ExpandableListView elvMain;
    AdapterHelper adapterHelper;
    SimpleExpandableListAdapter adapter;

    String c1;
    TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v  = (TextView)findViewById(R.id.textView);
        mapArrayListOfHrefs = new ArrayList<Map<String, String>>();
        mapArrayListOfHrefsForSpecificProduct = new ArrayList<ArrayList<Map<String, String>>>();
        //elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        //adapterHelper = new AdapterHelper(this,mapArrayListOfHrefsForSpecificProduct, mapArrayListOfHrefs);
        //adapter = adapterHelper.getAdapter();
      //  elvMain = (ExpandableListView)findViewById(R.id.elvMain);

        new NewThread(this).execute();

        //adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.item, titleList);
    }


    public class NewThread extends AsyncTask<Void, Integer, Void> {

        private Context mContext;

        public NewThread (Context context){
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {


            String c = "asdf";
            c1 = c;


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
            ArrayList<Map<String ,String>> temporaryInnerList;
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
        protected void onProgressUpdate(Integer... values){
         //   adapterHelper = new AdapterHelper(mContext,mapArrayListOfHrefsForSpecificProduct, mapArrayListOfHrefs);
         //   adapter = adapterHelper.getAdapter();
         //   elvMain = (ExpandableListView)findViewById(R.id.elvMain);
         //   elvMain.setAdapter(adapter);
        }

        @Override
        protected void onPostExecute(Void result) {
            v.setText(c1);
            adapterHelper = new AdapterHelper(mContext,mapArrayListOfHrefsForSpecificProduct, mapArrayListOfHrefs);
            adapter = adapterHelper.getAdapter();
            elvMain = (ExpandableListView)findViewById(R.id.elvMain);
            elvMain.setAdapter(adapter);
        }
    }
}