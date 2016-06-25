package com.example.parsertest;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {



    public ArrayList<Map<String ,String>> mapArrayListOfHrefs;
    public ArrayList<ArrayList<Map<String , String>>> mapArrayListOfHrefsForSpecificProduct;
    public ArrayList<ArrayList<ProductComponents>> oneProduct;

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

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
        Log.d("a","a");
        Log.d("a","a");
        mapArrayListOfHrefs = new ArrayList<Map<String, String>>();
        mapArrayListOfHrefsForSpecificProduct = new ArrayList<ArrayList<Map<String, String>>>();
        oneProduct = new ArrayList<ArrayList<ProductComponents>>(4);
        //elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        //adapterHelper = new AdapterHelper(this,mapArrayListOfHrefsForSpecificProduct, mapArrayListOfHrefs);
        //adapter = adapterHelper.getAdapter();
      //  elvMain = (ExpandableListView)findViewById(R.id.elvMain);

        new NewThread(this).execute();

        //adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.item, titleList);

/*
        AssetManager am = this.getAssets();
        try {
            InputStream is = am.open("t.txt");
            byte[] buf = new byte[is.available()];
            is.read(buf);
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i < buf.length; i++){
                sb.append(buf[i]);
                sb.append(" ");
            }

            Log.d("sd0", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        String FILE_NAME = "test";
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write("qwe".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public class NewThread extends AsyncTask<Void, Integer, Void> {

        private Context mContext;

        public NewThread (Context context){
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {

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
            Log.d("finish", "prev");




            Iterator<Map<String, String>> it = mapArrayListOfHrefs.iterator();
            Iterator<Map<String, String>> it1 = mapArrayListOfHrefs.iterator();

            String[] FILE_NAMES  = new String[mapArrayListOfHrefs.size()];
            String[] FILE_NAMES2  = new String[mapArrayListOfHrefs.size()];

            //   Map.Entry<String ,String > entry = it.next().entrySet().iterator().next();

            for (int i = 0; i < FILE_NAMES.length; i++){

                FILE_NAMES[i] = new String(it.next().entrySet().iterator().next().getValue());
                FILE_NAMES2[i] = new String(it1.next().entrySet().iterator().next().getKey());

                Log.d("names", FILE_NAMES[i]);
            }

            for (int i = 0; i < FILE_NAMES.length; i++){
                Pattern p = Pattern.compile("(\\s+|\\,*)");
                Matcher m = p.matcher(FILE_NAMES[i]);

                boolean result1 = m.find();
                if(result1){
                    StringBuffer st = new StringBuffer();
                    while (result1){
                        m.appendReplacement(st, "");
                        result1 = m.find();
                    }
                    m.appendTail(st);
                    FILE_NAMES[i] = st.toString();
                }

                Log.d("names_NEW", FILE_NAMES[i]);
            }


            dbHelper = new DBHelper(getApplicationContext(), FILE_NAMES);

            sqLiteDatabase = dbHelper.getWritableDatabase();

            Iterator<ArrayList<Map<String , String >>> iterOut = mapArrayListOfHrefsForSpecificProduct.iterator();
            Iterator<ArrayList<Map<String , String >>> iterOut1 = mapArrayListOfHrefsForSpecificProduct.iterator();

            Iterator<Map<String ,String >> iterIn = iterOut.next().iterator();
            Iterator<Map<String ,String >> iterIn1 = iterOut1.next().iterator();




            //     SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            for (int i = 0; i < FILE_NAMES.length; i++){
                ContentValues contentValues = new ContentValues();
                ContentValues contentValues2 = new ContentValues();
                contentValues.put("name", FILE_NAMES[i]);
                contentValues.put("ref", FILE_NAMES2[i]);


                sqLiteDatabase.insert("mapArrayListOfRefs", null, contentValues);

                while (iterIn.hasNext()){
                    contentValues2.put("name", iterIn.next().entrySet().iterator().next().getValue());
                    contentValues2.put("ref", iterIn1.next().entrySet().iterator().next().getKey());
                    sqLiteDatabase.insert(FILE_NAMES[i], null, contentValues2);
                }
                if (iterOut.hasNext()){
                    iterIn = iterOut.next().iterator();
                    iterIn1 = iterOut1.next().iterator();
                }


            }




            Log.d("finish", "result1");

            Map<String ,String> nutritionalValueMap = new HashMap<String, String>();
            Map<String ,String> vitaminsMap = new HashMap<String, String>();
            String[] macronutrients = new String[TableNamesData.getMacronutrientsLength()];
            String[] microelements = new String [TableNamesData.getMicroelementsLength()];

            nutritionalValueMap = TableNamesData.getNutritionalValueMap();
            vitaminsMap = TableNamesData.getVitaminsMap();
            macronutrients = TableNamesData.getMacronutrients();
            microelements = TableNamesData.getMicroelements();

            int j = 0;
            for (ArrayList<Map<String , String >> ar : mapArrayListOfHrefsForSpecificProduct){
                for(Map<String ,String > m : ar){
                    String s = m.entrySet().iterator().next().getKey();
                    oneProduct = new ParserHelper(s).getOneProductArrayList();

                    String[] productTables = new String[] {"Nutritional_value", "Vitamins", "Macronutrients", "Microelements"};
                    String[] columnsNames;
                    int i = 0;

                    Iterator<ArrayList<ProductComponents>> iterator = oneProduct.iterator();

                    ArrayList<ProductComponents> temp = iterator.next();

                    ContentValues contentValues = new ContentValues();
                    Cursor c = sqLiteDatabase.query("Nutritional_value",null,null,null,null,null,null);
                    columnsNames = c.getColumnNames();
                 //   Arrays.asList(columnsNames).contains()

                    for (ProductComponents p : temp){
                        //Log.d("BEFNUTR", p.name);
                       // Log.d("NUTR", nutritionalValueMap.get(p.name));
                       // SystemClock.sleep(10000);
                        boolean check = Arrays.asList(columnsNames).contains(nutritionalValueMap.get(p.name));
                        if(check)
                            contentValues.put(nutritionalValueMap.get(p.name), p.amount.toString() + p.measure);

                      //  Log.d("NUTRVAL", contentValues.toString());
                    }
                        sqLiteDatabase.insert("Nutritional_value", null, contentValues);

                    i++;
                    Log.d("NUTRVAL", String.valueOf(i));
                    if(i==10)
                        break;
/*
                    Log.d("  ",   "   ");

                    temp = iterator.next();
                    for (ProductComponents p : temp){
                        Log.d("VITAMINS", p.tempStr);
                    }

                    Log.d("  ", "   ");
*/


                    /*for (ArrayList<ProductComponents> arrayList : oneProduct){


                        ContentValues contentValues = new ContentValues();
                        Cursor c = sqLiteDatabase.query(productTables[i],null,null,null,null,null,null);
                        columnsCount = c.getColumnNames();
                        for (ProductComponents p : arrayList){
                            if(nutritionalValueMap.containsKey(p.name) || vitaminsMap.containsKey(p.name) || macronutrients.toString().contains(p.name) || microelements.toString().contains(p.name))
                                contentValues.put(p.name, p.amount.toString() + p.measure);

                            //Log.d("product", p.tempStr);
                        }

                        if(contentValues.size()>0){
                            sqLiteDatabase.insert(productTables[i], null, contentValues);
                            Log.d("finish", "result213");
                        }

                        i++;

                        //Log.d("product", "--------------------");
                    }
*/


                }

            }

            Log.d("finish", "result");




/*

            String[] FILE_NAMES2 ={"Пищевая ценность","Витамины","Макроэлементы","Микроэлементы"};

            try{
                int i = 0;

                for(ArrayList<ProductComponents> arrayList : oneProduct){
                    FileOutputStream fos = openFileOutput(FILE_NAMES2[i], Context.MODE_PRIVATE);
                    for (ProductComponents k : arrayList){
                        fos.write((k.tempStr + "\n").getBytes());
                    }
                    fos.close();
                    i++;
                }

            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/



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



/*
            // String FILE_NAME = "test";
            try {
                // TODO check exeptions.
                int i = 0;
                for (ArrayList<Map<String , String>> ar : mapArrayListOfHrefsForSpecificProduct){
                    FileOutputStream fos = openFileOutput(FILE_NAMES[i], Context.MODE_PRIVATE);
                    for (Map<String, String> m : ar){
                        fos.write((m.entrySet().iterator().next().getValue() + "\n").getBytes());

                    }
                    fos.close();
                    i++;
                }
                // fos.write("qwe".getBytes());



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("finish", "result1");

*/


            /*
            v.setText(c1);
            adapterHelper = new AdapterHelper(mContext,mapArrayListOfHrefsForSpecificProduct, mapArrayListOfHrefs);
            adapter = adapterHelper.getAdapter();
            elvMain = (ExpandableListView)findViewById(R.id.elvMain);
            elvMain.setAdapter(adapter);
            */
        }
    }
}