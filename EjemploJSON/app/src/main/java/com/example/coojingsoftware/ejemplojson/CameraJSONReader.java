package com.example.coojingsoftware.ejemplojson;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjgomez on 01/05/2016.
 */
public class CameraJSONReader{

    public CameraJSONReader(){

    }

    //Método que lee apartado a apartado dentro del JSON
    public List readJSONMsg(JsonReader reader) throws IOException {
        //Lista de camaras
        List camaras = new ArrayList();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            Log.i("MENSAJE","LLEGO A JSON");
            if (name.equals("features")) {
                //Estamos en el array de camaras
                Log.i("MENSAJE","ENTRO EN FEATURES");
                reader.beginArray();
                while (reader.hasNext()) {
                    camaras.add(readCamara(reader));
                    Log.i("MENSAJE","CAMARA AÑADIDA");
                }
                reader.endArray();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return camaras;
    }

    public Camara readCamara(JsonReader reader) throws IOException {

        int tipo=0;
        String url="";
        String desc="";

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("properties")) {
                //Estamos dentro de las propiedades de la camara que vamos a leer
                reader.beginObject();
                while (reader.hasNext()) {
                    String propiedad = reader.nextName();
                    if (propiedad.equals("tipo")) {
                        tipo = reader.nextInt();
                    }else if(propiedad.equals("url_trafico")) {
                        url = reader.nextString();
                    }else if(propiedad.equals("descrip")) {
                        desc = reader.nextString();
                        Log.i("MENSAJE",desc);
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Camara(tipo, url, desc);
    }
}
