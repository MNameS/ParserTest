package com.example.parsertest;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Сергей on 21.12.2015.
 */
// class for getting the list of refs and names of specific product
public class ParserForProductsInGroups {

    //ref that get from PareserOfMainProductsGroup
    String hrefForSpecificProduct;
    // list of refs for specific product
    ArrayList<Map<String, String >> mapArrayList;

    ParserForProductsInGroups(String hrefForSpecificProduct){
        Document doc;
        this.hrefForSpecificProduct = hrefForSpecificProduct;
        try {
            Log.d("Ref for product", hrefForSpecificProduct);
            doc = Jsoup.connect(this.hrefForSpecificProduct).get();
            Elements tables;

            mapArrayList = new ArrayList<Map<String, String>>();
            HashMap<String, String > map;
            // choose all tables with products
            tables = doc.select("tbody:contains(гр.)");
            String temp1,temp2;
            for (Element element : tables){
                for (Element e : element.select("tr")){
                    map = new HashMap<String, String>();
                    // fill list by refs and names
                    temp1 = e.select("a").attr("href");
                    temp2 = e.select("a").text();
                    if(!temp1.isEmpty()){
                        map.put("http://health-diet.ru/"+temp1,temp2);
                        mapArrayList.add(map);
                    }
                }
            }

            for (Map<String , String > m : mapArrayList) {
                Log.d("Map filling: ", m.entrySet().iterator().next().getKey() + " " + m.entrySet().iterator().next().getValue() + " td");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Map<String, String >> getMapArrayList(){
        return this.mapArrayList;
    }
}
