package fr.eseo.course.data.models;

/**
 * Created by amin on 17/01/2018.
 */

public class PageCharacter {

    private String count;
    private String next;
    private String previous;
    private Character[] results;

    public Character[] getCharactersTab() {
        return results;
    }

    public void setCharactersTab(Character[] charactersTab) {
        this.results = charactersTab;
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
