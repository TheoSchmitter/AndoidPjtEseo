package fr.eseo.course.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amin on 13/01/2018.
 */

public class CharactersList {

    private Character[] results;

    public Character[] getCharactersTab() {
        return results;
    }

    public void setCharactersTab(Character[] charactersTab) {
        this.results = charactersTab;
    }



}
