package fr.eseo.course.data.models;

/**
 * Created by amin on 17/01/2018.
 */

public class PagePlanet {

    private String count;
    private String next;
    private String previous;
    private Planet[] results;

    public Planet[] getPlanetsTab() {
        return results;
    }

    public void setPlanetsTab(Planet[] planetsTab) {
        this.results = planetsTab;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
