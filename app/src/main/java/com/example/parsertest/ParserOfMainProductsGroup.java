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
// class for getting the list of refs for product groups
public class ParserOfMainProductsGroup {

    ArrayList<Map<String, String >> mapArrayListOfHrefs;

    ParserOfMainProductsGroup(){
        Document doc;
        try {

            doc = Jsoup.connect("http://health-diet.ru/base_of_food/").get();
            Element table;
            Elements rows;

            mapArrayListOfHrefs = new ArrayList<Map<String, String>>();
            Map<String ,String > map;

            // get table that we need
            table = doc.getElementsByTag("tbody").get(8);
            // tr's tags all are chilfren of tbody
            rows  = table.getElementsByTag("tr");
            // temporary string
            String href;
            String text;
            // pass tbody children
            for(Element r : rows){
                map = new HashMap<String, String>();

                // fill the map by ref and name of product group
                href = r.getElementsByTag("td").get(1).select("a").attr("href");
                text =  r.getElementsByTag("td").get(1).text();
                map.put("http://health-diet.ru/" + href,text);
                mapArrayListOfHrefs.add(map);

                map = new HashMap<String, String>();
                href = r.getElementsByTag("td").get(3).select("a").attr("href");
                text = r.getElementsByTag("td").get(3).text();
                map.put("http://health-diet.ru/" + href,text);
                mapArrayListOfHrefs.add(map);
                //
            }

          /*  for (Map<String ,String> m : mapArrayListOfHrefs){
                Log.d("Map filling: ", m.entrySet().iterator().next().getKey() + " " + m.entrySet().iterator().next().getValue());
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Map<String, String >> getMapArrayListOfHrefs(){
        return this.mapArrayListOfHrefs;
    }
}
