package com.example.parsertest;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Сергей on 25.12.2015.
 */
public class AdapterHelper  {

    final String PRODUCT_GROUPS = "product groups";
    final String SPECIFIC_PRODUCT = "specific product";

    ArrayList<Map<String ,String>> mapArrayListOfHrefs;
    ArrayList<ArrayList<Map<String , String>>> mapArrayListOfHrefsForSpecificProduct;
    ArrayList<ArrayList<Map<String , String>>> mapArrayListOfHrefsForSpecificProductToSetForExpandebleListView;
    ArrayList<Map<String ,String>> temporaryHelperForMapArrayListOfHrefs;

    ArrayList<Map<String ,String>> gr;

    Map<String , String > temporaryHeperMap;

    Context context;

    AdapterHelper(Context context,
                    ArrayList<ArrayList<Map<String,
                    String>>> mapArrayListOfHrefsForSpecificProduct,
                    ArrayList<Map<String ,String>> mapArrayListOfHrefs){
        this.context = context;
        this.mapArrayListOfHrefs = mapArrayListOfHrefs;
        this.mapArrayListOfHrefsForSpecificProduct = mapArrayListOfHrefsForSpecificProduct;
    }

    SimpleExpandableListAdapter adapter;

    SimpleExpandableListAdapter getAdapter(){

     //   mapArrayListOfHrefs = new ArrayList<Map<String, String>>();
        gr = new ArrayList<Map<String, String>>();
        for (Map<String ,String > m : mapArrayListOfHrefs){
            temporaryHeperMap = new HashMap<String, String>();
            temporaryHeperMap.put(PRODUCT_GROUPS, m.entrySet().iterator().next().getValue());
            gr.add(temporaryHeperMap);
        }

        String groupFrom[] = new String[]{PRODUCT_GROUPS};
        int groupTo[] = new int[]{R.id.productGroupView};

        temporaryHelperForMapArrayListOfHrefs = new ArrayList<Map<String, String>>();

       // mapArrayListOfHrefsForSpecificProduct = new ArrayList<ArrayList<Map<String, String>>>();

        mapArrayListOfHrefsForSpecificProductToSetForExpandebleListView = new ArrayList<ArrayList<Map<String, String>>>();

        for (ArrayList<Map<String , String >> mapArrayList : mapArrayListOfHrefsForSpecificProduct){

            for (Map<String , String > m : mapArrayList){
                temporaryHeperMap = new HashMap<String, String>();
                temporaryHeperMap.put(SPECIFIC_PRODUCT, m.entrySet().iterator().next().getValue());
                temporaryHelperForMapArrayListOfHrefs.add(temporaryHeperMap);
            }
            mapArrayListOfHrefsForSpecificProductToSetForExpandebleListView.add(temporaryHelperForMapArrayListOfHrefs);


        }
        String productFrom[] = new String[]{SPECIFIC_PRODUCT};
        int productTo[] = new int[]{R.id.productView};

        adapter = new SimpleExpandableListAdapter(context, gr, R.layout.outer_item,
                groupFrom,
                groupTo,
                mapArrayListOfHrefsForSpecificProductToSetForExpandebleListView,
                R.layout.item,
                productFrom,productTo);

        return adapter;
     //   m = new HashMap<String, String>();

    }

}
