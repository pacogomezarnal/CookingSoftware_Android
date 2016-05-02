package com.example.coojingsoftware.ejemplojson;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CargaJSONActivity extends AppCompatActivity {
    //Enlace a datos
    private static String urlCamaras = "http://mapas.valencia.es/lanzadera/opendata/Tra-camaras/JSON";
    //TextView temporal
    TextView mens;
    //JSON reader
    JsonReader reader;
    //Listado de camaras
    List camaras;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_carga_json);

        //Lanzamos la peticion de consulta
        new JSONParse().execute();
    }


    //Inner class que realizará de forma asíncrona(BackGround) la consulta a la dirección HTTP
    private class JSONParse extends AsyncTask<String, String, JsonReader> {
        HttpURLConnection urlConnection = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mens = (TextView) findViewById(R.id.msg);
        }

        //Esta clase realizará la conexión devolviendo el JSON
        @Override
        protected JsonReader doInBackground(String... args) {
            String temp = "";
            try {
                URL url = new URL(urlCamaras);
                urlConnection = (HttpURLConnection) url.openConnection();
                temp = urlConnection.getResponseMessage();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
                CameraJSONReader cJSONReader=new CameraJSONReader();
                try {
                    camaras = cJSONReader.readJSONMsg(reader);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
                return reader;
            }
        }

        @Override
        protected void onPostExecute(JsonReader reader) {
            CameraJSONReader cJSONReader=new CameraJSONReader();
                mens.setText("Numero de camaras recogidas: "+camaras.size());
        }
    }

}

