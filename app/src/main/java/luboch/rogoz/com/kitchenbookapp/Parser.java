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

    public void setApplications(ArrayList<KitchenRecipe> recipes) {
        this.recipes = recipes;
    }

    public Parser(XmlPullParser xpp) {
        this.xpp=xpp;
        //this.xmlData = xmlData;
        recipes = new ArrayList<KitchenRecipe>();
    }

    public boolean process() {
        boolean status = true;
        KitchenRecipe currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);

//            Resources res = act
//            XmlPullParser xpp = getResources().getXml(R.xml.kitchenBook);
            //XmlPullParser xpp = factory.newPullParser();
            //xpp.setInput(new StringReader(this.xmlData));

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
//                        Log.d("ParseApplications","Starting tag for " + tagName);
                        if (tagName.equalsIgnoreCase("recipe")) {
                            inEntry = true;
                            currentRecord = new KitchenRecipe();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
//                        Log.d("ParseApplications","Ending tag for " + tagName);
                        if (inEntry) {
                            if (tagName.equalsIgnoreCase("recipe")) {
                                recipes.add(currentRecord);
                                inEntry = false;
                            } else if (tagName.equalsIgnoreCase("title")) {
                                currentRecord.setTitle(textValue);
                            } else if (tagName.equalsIgnoreCase("author")) {
                                currentRecord.setAuthor(textValue);
                            } else if (tagName.equalsIgnoreCase("category")){
                                currentRecord.setCategory(textValue);
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
            Log.d("ParseApplications", "*************");
            Log.d("ParseApplications", "Title " + rec.getTitle());
            Log.d("ParseApplications", "Aythor " + rec.getAuthor());
            Log.d("ParseApplications", "Category " + rec.getCategory());
        }
        return true;
    }
}
