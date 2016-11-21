package luboch.rogoz.com.kitchenbookapp;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

/**
 * Created by Mic on 2016-11-21.
 */

public class Parser {
    //private String xmlData;
    private ArrayList<KitchenRecipe> recipes;
    private XmlPullParser xpp;

    public ArrayList<KitchenRecipe> getRecipes() {
        return recipes;
    }

    public Parser(XmlPullParser xpp) {
        this.xpp=xpp;
        //this.xmlData = xmlData;
        recipes = new ArrayList<KitchenRecipe>();
    }

    public boolean process() {
        boolean status = true;
        KitchenRecipe currentRecord = null;
        boolean inRecipe = false;
        boolean inIngredients = false;
        String textValue = "";

        try {

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
//                        Log.d("ParseRecipes","Starting tag for " + tagName);
                        if (tagName.equalsIgnoreCase("recipe")) {
                            inRecipe = true;
                            currentRecord = new KitchenRecipe();
                        }
                        if (tagName.equalsIgnoreCase("ingredientList")){
                            inIngredients = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
//                        Log.d("arseRecipes","Ending tag for " + tagName);
                        if (inRecipe) {
                            if (tagName.equalsIgnoreCase("recipe")) {
                                recipes.add(currentRecord);
                                inRecipe = false;
                            } else if (tagName.equalsIgnoreCase("title")) {
                                currentRecord.setTitle(textValue);
                            } else if (tagName.equalsIgnoreCase("author")) {
                                currentRecord.setAuthor(textValue);
                            } else if (tagName.equalsIgnoreCase("category")){
                                currentRecord.setCategory(textValue);
                            } else if (tagName.equalsIgnoreCase("description")){
                                currentRecord.setDescription(textValue);
                            }
                        }
                        if (inIngredients){
                            if(tagName.equalsIgnoreCase("description")){
                                inIngredients = false;
                            }
                            if(tagName.equalsIgnoreCase("ingriedient")){
                                currentRecord.addIngredient(textValue);
                               // Log.d("ParseRecipes","Add ingriedient " + textValue);
                            }
                        }
                        break;

                    default:
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        for(KitchenRecipe rec : recipes){
            Log.d("ParseRecipes", "*************");
            Log.d("ParseRecipes", "Title " + rec.getTitle());
            Log.d("ParseRecipes", "Author " + rec.getAuthor());
            Log.d("ParseRecipes", "Category " + rec.getCategory());
            Log.d("ParseRecipes", "Ingriendients " + rec.getIngredients());
            Log.d("ParseRecipess", "Description: " + rec.getDescription());
        }
        return true;
    }
}
