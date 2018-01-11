package com.example.ionut.appandroid;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    String username, location;
    String URL;
    NodeList nodelist;
    ListView listView;
    List<String> value = new ArrayList<String>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        username = getIntent().getStringExtra("Username");
        location = getIntent().getStringExtra("Location");
        URL = "http://api.geonames.org/wikipediaSearch?q=" + location + "&maxRows=10&username=demos&style=full";
        new DownloadXML().execute(URL);

    }

    private class DownloadXML extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... Url) {
            try {
                URL url = new URL(Url[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                if (db == null) {
                    throw new Exception("No Download Document");
                }
                // Download the XML file
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
                System.out.println("!!" + doc.getElementsByTagName("entry").getLength());
                // Locate the Tag Name
                if (doc != null) {
                    nodelist = doc.getElementsByTagName("entry");
                } else {
                    throw new Exception("No Download");
                }

            } catch (Exception e) {
                System.out.println("---------------- !!!!! --------------");
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            for (int temp = 0; temp < nodelist.getLength(); temp++) {
                Node nNode = nodelist.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // Set the texts into TextViews from item nodes
                    // Get the title
//                    System.out.println("Title:::::::" + getNode("title", eElement).toString());
                    String values = getNode("title", eElement).toString();
                    value.add(values);

                }
            }
            Intent intent = new Intent(getBaseContext(), ListActivity.class);
            intent.putStringArrayListExtra("list", (ArrayList<String>) value);
            startActivity(intent);

        }
    }

    private static String getNode(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }

}
