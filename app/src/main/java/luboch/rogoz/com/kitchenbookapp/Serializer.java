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


            xmlSerializer.endTag("","recipe");
            i++;        }
//        // open tag: <study>
//        xmlSerializer.startTag("", Study.STUDY);
//        xmlSerializer.attribute("", Study.ID, String.valueOf(study.mId));
//
//        // open tag: <topic>
//        xmlSerializer.startTag("", Study.TOPIC);
//        xmlSerializer.text(study.mTopic);
//        // close tag: </topic>
//        xmlSerializer.endTag("", Study.TOPIC);
//
//        // open tag: <content>
//        xmlSerializer.startTag("", Study.CONTENT);
//        xmlSerializer.text(study.mContent);
//        // close tag: </content>
//        xmlSerializer.endTag("", Study.CONTENT);
//
//        // open tag: <author>
//        xmlSerializer.startTag("", Study.AUTHOR);
//        xmlSerializer.text(study.mAuthor);
//        // close tag: </author>
//        xmlSerializer.endTag("", Study.AUTHOR);
//
//        // open tag: <date>
//        xmlSerializer.startTag("", Study.DATE);
//        xmlSerializer.text(study.mDate);
//        // close tag: </date>
//        xmlSerializer.endTag("", Study.DATE);
//
//        // close tag: </study>
//        xmlSerializer.endTag("", Study.STUDY);
//        // close tag: </record>
        xmlSerializer.endTag("", "kitchen_book");

        // end DOCUMENT
        xmlSerializer.endDocument();

        return writer.toString();

    }
}
