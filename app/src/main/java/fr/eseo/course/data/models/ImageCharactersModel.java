package fr.eseo.course.data.models;

import android.os.Parcelable;

/**
 * Created by amin on 11/01/2018.
 */

public class ImageCharactersModel {

    private int srcid;
    private String name;


    public ImageCharactersModel(int src, String name)
    {
        this.srcid = src;
        this.name = name;
    }
    public int getSrc() {
        return this.srcid;
    }

    public void setSrc(int src) {
        this.srcid = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
