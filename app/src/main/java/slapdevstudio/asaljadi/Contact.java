package slapdevstudio.asaljadi;

/**
 * Created by User on 11/07/2015.
 */
public class Contact {
    private String name;
    private String number;
    private String id;
    boolean selected = false;

    public Contact(String name, String number, String id) {
        this.name = name;
        this.number = number;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getId() {
        return id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
