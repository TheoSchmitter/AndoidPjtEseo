package fr.eseo.course.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amin on 17/01/2018.
 */

public class SaveDataRequest {

    private List<Character> charactersList;
    private List<Planet> planetList;
    private Planet selectedPlanet = new Planet();
    private Character selectedCharacter = new Character();

    private SaveDataRequest(){
        charactersList = new ArrayList<>();
        planetList = new ArrayList<>();
    }

    /** Instance unique pré-initialisée */
    private static SaveDataRequest INSTANCE = new SaveDataRequest();

    /** Point d'accès pour l'instance unique du singleton */
    public static SaveDataRequest getInstance()
    { return INSTANCE;
    }



    public List<Character> getCharactersList() {
        return charactersList;
    }

    public void setCharactersList(List<Character> charactersList) {
        this.charactersList = charactersList;
    }

    public List<Planet> getPlanetList() {
        return planetList;
    }

    public void setPlanetList(List<Planet> planetList) {
        this.planetList = planetList;
    }

    public Planet getSelectedPlanet() {
        return selectedPlanet;
    }

    public void setSelectedPlanet(Planet selectedPlanet) {
        this.selectedPlanet = selectedPlanet;
    }

    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(Character selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
}
