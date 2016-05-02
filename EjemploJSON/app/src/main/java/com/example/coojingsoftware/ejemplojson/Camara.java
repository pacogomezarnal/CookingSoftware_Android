package com.example.coojingsoftware.ejemplojson;

/**
 * Created by fjgomez on 01/05/2016.
 */
public class Camara {

    private int tipo;
    private String url;
    private String desc;

    public Camara(int tipo,String url,String desc){
        this.tipo=tipo;
        this.url=url;
        this.desc=desc;
    }
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
