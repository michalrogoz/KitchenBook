package luboch.rogoz.com.kitchenbookapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnParse;
    private Button btnSave;
    private ListView listRecipes;
    private ArrayList<KitchenRecipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnParse = (Button) findViewById(R.id.btnParse);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                XmlPullParser xpp = getResources().getXml(R.xml.kitchen_book);
                Parser parser = new Parser(xpp);
                parser.process();
                recipes = parser.getRecipes();

                ArrayAdapter<KitchenRecipe> arrayAdapter = new ArrayAdapter<KitchenRecipe>(
                         MainActivity.this, R.layout.list_item, recipes);

                listRecipes.setAdapter(arrayAdapter);

            }
        });

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Serializer serializer = new Serializer();
                try {
                    String XMLcontent = serializer.process(recipes);
                    FileOutputStream fOut = openFileOutput("XML.txt", MODE_WORLD_READABLE);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);
                    osw.write(XMLcontent);
                    osw.close();

                    Log.d("File handler","File created");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        listRecipes = (ListView) findViewById(R.id.xmlListView);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
