package fr.eseo.course.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amin on 10/01/2018.
 */

public class Character {

    private String name;

    private String height;

    private String mass;

    private String hair_color;

    private String gender;

    public Character(){

    }

    public Character(String name, String height, String mass, String hair_color, String gender) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair_color = hair_color;
        this.gender = gender;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHaircolor() {
        return hair_color;
    }

    public void setHaircolor(String haircolor) {
        this.hair_color = haircolor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
