package com.example.parsertest;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Сергей on 21.12.2015.
 */
// class for getting data about product(outer arraylist of "nutritional value, vitamines, macronutrients, microelements")
public class ParserHelper {


    protected ArrayList<ArrayList<ProductComponents>> oneProduct = new ArrayList<ArrayList<ProductComponents>>(4);

    // ref for current page
    String htmlPagePath;

    public ParserHelper(String htmlPagePath){
        Document doc;
        String LOG_TAG = "MyLog";
        this.htmlPagePath = htmlPagePath;
        try {

            Log.d(LOG_TAG + " ref: ", htmlPagePath);

            doc = Jsoup.connect(this.htmlPagePath).get();
            Elements elements;
            // get tables with necessary components
            elements = doc.select("div[class=cnt_main_block_content]");

            // need "i" because we have more div tags that necessary
            int i = 0;
            oneProduct = new ArrayList<ArrayList<ProductComponents>>(4);
            ArrayList<ProductComponents> innerProduct;

            String tempStr;

            // fill "oneProduct" array
            for (Element r : elements){
                innerProduct = new ArrayList<ProductComponents>();
                Log.d(LOG_TAG, "the btginning of cycle foreach i = " + i);
                Elements tempElements = r.children();
                for(Element r1 : tempElements){
                    tempStr = r1.text();
                    innerProduct.add(new ProductComponents(tempStr));
                }
                for (ProductComponents ar : innerProduct){
                    Log.d(LOG_TAG, "finish of the inner cycle " + ar.tempStr);
                }
                oneProduct.add(innerProduct);
                i++;
                // because we need only 4 tables
                if(i==4)break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ArrayList<ProductComponents>> getOneProductArrayList(){
        return this.oneProduct;
    }

}
