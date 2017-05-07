package info.androidhive.materialdesign.adapter.Store;

/**
 * Created by deepak on 7/5/17.
 */

public class Store {
    private String name,location;

    public Store() {
    }

    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
