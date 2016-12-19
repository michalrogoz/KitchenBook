package luboch.rogoz.com.kitchenbookapp;

import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Created by Mic on 2016-12-19.
 */

public class Serializer {

    public String process(ArrayList<KitchenRecipe> recipes) throws IOException {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();

        xmlSerializer.setOutput(writer);
        // start DOCUMENT
        xmlSerializer.startDocument("UTF-8", true);
        // open tag: <record>
        xmlSerializer.startTag("", "kitchen_book");

        int i = 1;

        for (KitchenRecipe recipe : recipes) {
            xmlSerializer.startTag("","recipe");
            xmlSerializer.attribute("","id",String.valueOf(i));

            xmlSerializer.startTag("", "title");
            xmlSerializer.text(recipe.getTitle());
            xmlSerializer.endTag("", "title");

            xmlSerializer.startTag("", "author");
            xmlSerializer.text(recipe.getAuthor());
            xmlSerializer.endTag("", "author");

            xmlSerializer.startTag("", "category");
            xmlSerializer.text(recipe.getCategory());
            xmlSerializer.endTag("", "category");

            ArrayList<String> ingredients = recipe.getIngredient();

            xmlSerializer.startTag("","ingredientList");
            for (String ing : ingredients) {
                xmlSerializer.startTag("", "ingriedient");
                xmlSerializer.text(ing);
                xmlSerializer.endTag("", "ingriedient");
            }
            xmlSerializer.endTag("","ingredientList");

            xmlSerializer.startTag("", "description");
            xmlSerializer.text(recipe.getDescription());
            xmlSerializer.endTag("", "description");

            xmlSerializer.endTag("","recipe");
            i++;
        }

        xmlSerializer.endTag("", "kitchen_book");

        // end DOCUMENT
        xmlSerializer.endDocument();

        return writer.toString();

    }
}
